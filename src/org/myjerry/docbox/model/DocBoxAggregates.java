package org.myjerry.docbox.model;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;

public class DocBoxAggregates {
	
	public static long aggregateKey = -1;
	
	private long numFolders = 0;
	
	private long numFiles = 0;
	
	private long totalSize = 0;
	
	public static DocBoxAggregates fromEntity(Entity entity) {
		DocBoxAggregates agg = new DocBoxAggregates();
		
		agg.numFolders = (Long) entity.getProperty("numFolders");
		agg.numFiles = (Long) entity.getProperty("numFiles");
		agg.totalSize = (Long) entity.getProperty("totalSize");
		aggregateKey = entity.getKey().getId();
		
		return agg;
	}
	
	public Entity toEntity() {
		Entity entity;
		if(aggregateKey != -1) {
			entity = new Entity(DocBoxAggregates.class.getSimpleName(), aggregateKey);
		} else {
			entity = new Entity(DocBoxAggregates.class.getSimpleName());
		}
		
		entity.setProperty("numFolders", this.numFolders);
		entity.setProperty("numFiles", this.numFiles);
		entity.setProperty("totalSize", this.totalSize);
		
		return entity;
	}

	public long getNumFolders() {
		return numFolders;
	}

	public void setNumFolders(long numFolders) {
		this.numFolders = numFolders;
	}

	public long getNumFiles() {
		return numFiles;
	}

	public void setNumFiles(long numFiles) {
		this.numFiles = numFiles;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	
}
