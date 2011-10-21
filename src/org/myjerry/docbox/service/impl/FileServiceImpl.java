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

package org.myjerry.docbox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.myjerry.docbox.model.DocBoxFile;
import org.myjerry.docbox.service.FileService;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

/**
 * 
 * @author sangupta
 * @created 21 Oct 2011
 */
public class FileServiceImpl implements FileService {
	
	private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@Override
	public List<DocBoxFile> getFilesInFolder(long folderID) {
		Query query = new Query(DocBoxFile.class.getSimpleName());
		query.addFilter("parentFolderID", FilterOperator.EQUAL, folderID);
		
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		if(entities != null && entities.size() > 0) {
			List<DocBoxFile> files = new ArrayList<DocBoxFile>();
			for(Entity entity : entities) {
				files.add(DocBoxFile.fromEntity(entity));
			}
			
			return files;
		}
		
		return null;
	}

	@Override
	public DocBoxFile getFileDetails(Long fileID) {
		Key key = KeyFactory.createKey(DocBoxFile.class.getSimpleName(), fileID);
		Entity entity;
		try {
			entity = datastore.get(key);
			DocBoxFile file = DocBoxFile.fromEntity(entity);
			return file;
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
