package org.myjerry.docbox.web;

import java.util.HashMap;
import java.util.Map;

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
