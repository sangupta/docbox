package org.myjerry.docbox.web.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.docbox.BeanNames;
import org.myjerry.docbox.Beans;
import org.myjerry.docbox.model.DocBoxFile;
import org.myjerry.docbox.service.FileService;
import org.myjerry.docbox.web.JspPages;
import org.myjerry.docbox.web.ModelAndView;
import org.myjerry.docbox.web.RequestHandler;

public class FileDetailsHandlers implements RequestHandler {
	
	private FileService fileService = Beans.getBean(BeanNames.FILE_SERVICE);

	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String file = request.getParameter("file");
		Long fileID = Long.parseLong(file);
		DocBoxFile fileDetails = this.fileService.getFileDetails(fileID);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("file", fileDetails);
		mav.setViewName(JspPages.FILE_DETAILS_PAGE);
		
		return mav;
	}

}
