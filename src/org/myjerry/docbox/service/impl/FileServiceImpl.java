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
