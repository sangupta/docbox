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

package org.myjerry.docbox;

import java.util.HashMap;
import java.util.Map;

import org.myjerry.docbox.service.impl.FileServiceImpl;
import org.myjerry.docbox.service.impl.FolderServiceImpl;

/**
 * 
 * @author sangupta
 * @created 21 Oct 2011
 */
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
