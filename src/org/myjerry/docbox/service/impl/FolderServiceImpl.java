package org.myjerry.docbox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.myjerry.docbox.model.DocBoxFolder;
import org.myjerry.docbox.service.FolderService;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class FolderServiceImpl implements FolderService {
	
	private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@Override
	public List<DocBoxFolder> getRootFolder() {
		Query query = new Query(DocBoxFolder.class.getSimpleName());
		query.addFilter("parentFolderID", FilterOperator.EQUAL, DocBoxFolder.ROOT_FOLDER_ID);
		
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		if(entities != null && entities.size() > 0) {
			List<DocBoxFolder> folders = new ArrayList<DocBoxFolder>();
			for(Entity entity : entities) {
				folders.add(DocBoxFolder.fromEntity(entity));
			}
			
			return folders;
		}
		
		return null;
	}

	@Override
	public List<DocBoxFolder> getChildFolders(long folderID) {
		Query query = new Query(DocBoxFolder.class.getSimpleName());
		query.addFilter("parentFolderID", FilterOperator.EQUAL, folderID);
		
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		if(entities != null && entities.size() > 0) {
			List<DocBoxFolder> folders = new ArrayList<DocBoxFolder>();
			for(Entity entity : entities) {
				folders.add(DocBoxFolder.fromEntity(entity));
			}
			
			return folders;
		}
		
		return null;
	}

	@Override
	public long createFolder(String folderName, long parentFolderID) {
		DocBoxFolder folder = new DocBoxFolder();
		folder.setName(folderName);
		folder.setParentFolderID(parentFolderID);
		
		Entity entity = folder.toEntity();
		datastore.put(entity);
		
		return entity.getKey().getId();
	}

	@Override
	public DocBoxFolder getFolderDetails(long folderID) {
		Key key = KeyFactory.createKey(DocBoxFolder.class.getSimpleName(), folderID);
		Entity entity;
		try {
			entity = datastore.get(key);
			DocBoxFolder folder = DocBoxFolder.fromEntity(entity);
			return folder;
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
