package org.myjerry.docbox.service.impl;

import java.util.List;

import org.myjerry.docbox.model.DocBoxAggregates;
import org.myjerry.docbox.service.AggregateService;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

public class AggregateServiceImpl implements AggregateService {
	
	private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@Override
	public DocBoxAggregates getAggregates() {
		Query query = new Query(DocBoxAggregates.class.getSimpleName());
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1));

		if(entities != null && entities.size() == 1) {
			Entity entity = entities.get(0);
			return DocBoxAggregates.fromEntity(entity);
		}
		
		return new DocBoxAggregates();
	}

	@Override
	public void updateAggregates(int foldersAdded, int filesAdded, long sizeAdded) {
		DocBoxAggregates agg = getAggregates();
		
		agg.setNumFolders(agg.getNumFolders() + foldersAdded);
		agg.setNumFiles(agg.getNumFiles() + filesAdded);
		agg.setTotalSize(agg.getTotalSize() +  sizeAdded);
		
		datastore.put(agg.toEntity());
	}

}
