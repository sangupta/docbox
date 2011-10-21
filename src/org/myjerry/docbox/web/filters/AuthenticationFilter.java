package org.myjerry.docbox.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		UserService userService = UserServiceFactory.getUserService();

		if(userService.isUserLoggedIn()) {
			if(userService.isUserAdmin()) {
				// do nothing
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}
			
			// throw an access denied message
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You need to be an administrator to view the application console.");
			return;
		}
		
		// the user is not signed in
		// send the sign-in page
		String thisURL = request.getRequestURI();
		String redirectURL = userService.createLoginURL(thisURL);
		response.sendRedirect(redirectURL);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
