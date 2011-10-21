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

package org.myjerry.docbox.web;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sangupta
 * @created 21 Oct 2011
 */
public class ModelAndView {
	
	private final Map<String, Object> model = new HashMap<String, Object>();
	
	private String view;
	
	public ModelAndView() {
		// do nothing
	}
	
	public ModelAndView(String viewName) {
		this();
		this.view = viewName;
	}
	
	public ModelAndView(String viewName, Map<String, Object> model) {
		this.view = viewName;
		this.model.clear();
		
		if(model != null && model.size() > 0) {
			this.model.putAll(model);
		}
	}
	
	public Map<String, Object> getModel() {
		return model;
	}
	
	public void addObject(String name, Object value) {
		model.put(name, value);
	}
	
	public void clearModel() {
		model.clear();
	}
	
	public void setViewName(String viewName) {
		this.view = viewName;
	}
	
	public String getViewName() {
		return this.view;
	}

}
