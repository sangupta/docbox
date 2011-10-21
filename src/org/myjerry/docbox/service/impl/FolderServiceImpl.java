package org.myjerry.docbox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.myjerry.docbox.model.DocBoxFolder;
import org.myjerry.docbox.service.FolderService;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class FolderServiceImpl implements FolderService {
	
	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@Override
	public List<DocBoxFolder> getRootFolder() {
		Query query = new Query(DocBoxFolder.class.getSimpleName());
		query.addFilter("parentFolderID", FilterOperator.EQUAL, 0);
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
