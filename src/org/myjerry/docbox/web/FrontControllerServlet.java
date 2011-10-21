package org.myjerry.docbox.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.docbox.web.handlers.CreateFolderHandler;
import org.myjerry.docbox.web.handlers.FileDetailsHandlers;
import org.myjerry.docbox.web.handlers.FileUploadHandler;
import org.myjerry.docbox.web.handlers.HomePageHandler;

public class FrontControllerServlet extends HttpServlet {
	
	/**
	 * Generated via Eclipse
	 */
	private static final long serialVersionUID = 6261146111722181103L;
	
	private static final Map<String, RequestHandler> handlers = new HashMap<String, RequestHandler>();
	
	static {
		handlers.put("/index.html", new HomePageHandler());
		handlers.put("/uploadFile.html", new FileUploadHandler());
		handlers.put("/createFolder.html", new CreateFolderHandler());
		handlers.put("/fileDetails.html", new FileDetailsHandlers());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		RequestHandler handler = handlers.get(uri);
		if(handler != null) {
			ModelAndView mav = handler.process(request, response);
			if(mav == null) {
				// response has been handled by the handler
				// do nothing
				return;
			}
			
			// get the handler view name
			String viewName = mav.getViewName();
			if(viewName == null || viewName.trim().length() == 0) {
				throw new IllegalStateException("Request handler did not return a successful view name.");
			}
			
			String jsp;
			if(!viewName.startsWith("/")) {
				jsp = "/WEB-INF/jsps/" + viewName;
			} else {
				jsp = "/WEB-INF/jsps" + viewName;
			}
			
			// set the attributes
			Map<String, Object> model = mav.getModel();
			if(model.size() > 0) {
				 Set<Entry<String, Object>> entrySet = model.entrySet();
				for(Entry<String, Object> entry : entrySet) {
					request.setAttribute(entry.getKey(), entry.getValue());
				}
			}

			// foward to the view
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(jsp);
			requestDispatcher.forward(request, response);
			return;
		}
		
		throw new RuntimeException("No matching handler found for uri: " + uri);
	}

}
