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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.docbox.BeanNames;
import org.myjerry.docbox.Beans;
import org.myjerry.docbox.model.DocBoxFile;
import org.myjerry.docbox.service.AggregateService;
import org.myjerry.docbox.web.ModelAndView;
import org.myjerry.docbox.web.RequestHandler;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

/**
 * 
 * @author sangupta
 * @created 21 Oct 2011
 */
public class FileUploadHandler implements RequestHandler {
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	private BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
	
	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	private AggregateService aggregateService = Beans.getBean(BeanNames.AGGREGATE_SERVICE);
	
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
        long folderID = Long.parseLong(request.getParameter("currentFolderID"));
        BlobKey blobKey = blobs.get("fileBeingUploaded");
        
        if (blobKey == null) {
            response.sendRedirect("/index.html?folder=" + folderID);
            return null;
        }

        BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
        
        DocBoxFile file = new DocBoxFile(blobInfo);
        file.setParentFolderID(folderID);
        
        datastore.put(file.toEntity());

        this.aggregateService.updateAggregates(0, 1, file.getSize());
        
        response.sendRedirect("/index.html?folder=" + folderID);
        return null;
	}

}
