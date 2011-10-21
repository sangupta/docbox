/**
 * myJerry | DocBox
 * Copyright (C) 2011, myJerry Developers
 * http://www.myjerry.org/docbox
 * 
 * The file is licensed under the the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.myjerry.docbox.web.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.docbox.BeanNames;
import org.myjerry.docbox.Beans;
import org.myjerry.docbox.model.DocBoxFile;
import org.myjerry.docbox.service.FileService;
import org.myjerry.docbox.web.ModelAndView;
import org.myjerry.docbox.web.RequestHandler;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

/**
 * 
 * @author sangupta
 * @created 21 Oct 2011
 */
public class FileDownloadHandler implements RequestHandler {
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	private FileService fileService = Beans.getBean(BeanNames.FILE_SERVICE);

	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long fileID = Long.parseLong(request.getParameter("file"));
		Long folderID = Long.parseLong(request.getParameter("folder"));
		
		DocBoxFile file = this.fileService.getFileDetails(fileID);
		if(file != null && file.getParentFolderID() == folderID) {
			// allowed to be downloaded
			BlobKey blobKey = file.getBlobKey();
			this.blobstoreService.serve(blobKey, response);
			
			return null;
		}
		
		response.sendRedirect("/index.html?folder=" + folderID);
		return null;
	}

}
