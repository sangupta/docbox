package org.myjerry.docbox;

import java.util.HashMap;
import java.util.Map;

import org.myjerry.docbox.service.impl.FileServiceImpl;
import org.myjerry.docbox.service.impl.FolderServiceImpl;

public class Beans {
	
	private static final Map<String, Object> beans = new HashMap<String, Object>();
	
	static {
		beans.put(BeanNames.FOLDER_SERVICE, new FolderServiceImpl());
		beans.put(BeanNames.FILE_SERVICE, new FileServiceImpl());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) beans.get(beanName);
	}

}
