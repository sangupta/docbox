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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.docbox.BeanNames;
import org.myjerry.docbox.Beans;
import org.myjerry.docbox.model.DocBoxFile;
import org.myjerry.docbox.model.DocBoxFolder;
import org.myjerry.docbox.service.FileService;
import org.myjerry.docbox.service.FolderService;
import org.myjerry.docbox.web.JspPages;
import org.myjerry.docbox.web.ModelAndView;
import org.myjerry.docbox.web.RequestHandler;

/**
 * 
 * @author sangupta
 * @created 21 Oct 2011
 */
public class HomePageHandler implements RequestHandler {
	
	private FolderService folderService = Beans.getBean(BeanNames.FOLDER_SERVICE);
	
	private FileService fileService = Beans.getBean(BeanNames.FILE_SERVICE);
	
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
		String folder = request.getParameter("folder");
		long folderID;
		if(folder == null || folder.trim().length() == 0) {
			folderID = 0;
		} else {
			folderID = Long.parseLong(folder);
		}
		
		List<DocBoxFolder> rootFolders = this.folderService.getChildFolders(folderID);
		List<DocBoxFile> rootFiles = this.fileService.getFilesInFolder(folderID);
		
		DocBoxFolder currentFolder = null;
		if(folderID > 0) {
			currentFolder = this.folderService.getFolderDetails(folderID);
		}
		
		ModelAndView mav = new ModelAndView(JspPages.HOME_PAGE);
		mav.addObject("folders", rootFolders);
		mav.addObject("files", rootFiles);
		mav.addObject("currentFolderID", folderID);
		mav.addObject("currentFolder", currentFolder);
		
		return mav;
	}

}
