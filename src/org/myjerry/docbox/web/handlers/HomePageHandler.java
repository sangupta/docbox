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
