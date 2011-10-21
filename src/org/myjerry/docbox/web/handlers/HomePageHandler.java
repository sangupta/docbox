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
		List<DocBoxFolder> rootFolders = this.folderService.getRootFolder();
		List<DocBoxFile> rootFiles = this.fileService.getFilesInFolder(DocBoxFolder.ROOT_FOLDER_ID);
		
		ModelAndView mav = new ModelAndView(JspPages.HOME_PAGE);
		mav.addObject("folders", rootFolders);
		mav.addObject("files", rootFiles);
		
		return mav;
	}

}
