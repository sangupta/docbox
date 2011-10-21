package org.myjerry.docbox.web.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.docbox.BeanNames;
import org.myjerry.docbox.Beans;
import org.myjerry.docbox.service.FolderService;
import org.myjerry.docbox.web.ModelAndView;
import org.myjerry.docbox.web.RequestHandler;

public class CreateFolderHandler implements RequestHandler {
	
	private FolderService folderService = Beans.getBean(BeanNames.FOLDER_SERVICE);

	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String folderName = request.getParameter("folderName");
		Long parentFolder = Long.parseLong(request.getParameter("currentFolderID"));

		if(folderName != null && folderName.trim().length() > 0) {
			long folderID = this.folderService.createFolder(folderName, parentFolder);
			response.sendRedirect("/index.html?folder=" + folderID);
		} else {
			response.sendRedirect("/index.html?folder=" + parentFolder);
		}
		
		return null;
	}

}
