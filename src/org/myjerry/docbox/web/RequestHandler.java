package org.myjerry.docbox.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

public interface RequestHandler {
	
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException ;

}
