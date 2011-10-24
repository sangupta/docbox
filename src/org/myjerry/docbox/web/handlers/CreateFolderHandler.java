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
import org.myjerry.docbox.service.AggregateService;
import org.myjerry.docbox.service.FolderService;
import org.myjerry.docbox.web.ModelAndView;
import org.myjerry.docbox.web.RequestHandler;

/**
 * 
 * @author sangupta
 * @created 21 Oct 2011
 */
public class CreateFolderHandler implements RequestHandler {
	
	private FolderService folderService = Beans.getBean(BeanNames.FOLDER_SERVICE);
	
	private AggregateService aggregateService = Beans.getBean(BeanNames.AGGREGATE_SERVICE);

	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String folderName = request.getParameter("folderName");
		Long parentFolder = Long.parseLong(request.getParameter("currentFolderID"));

		if(folderName != null && folderName.trim().length() > 0) {
			long folderID = this.folderService.createFolder(folderName, parentFolder);
	        
			this.aggregateService.updateAggregates(1, 0, 0);
	        
	        response.sendRedirect("/index.html?folder=" + folderID);
		} else {
			response.sendRedirect("/index.html?folder=" + parentFolder);
		}
		
		return null;
	}

}
