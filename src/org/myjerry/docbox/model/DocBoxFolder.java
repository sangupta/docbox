package org.myjerry.docbox.model;

import com.google.appengine.api.datastore.Entity;

public class DocBoxFolder {
	
	public static long ROOT_FOLDER_ID = 0;
	
	private long folderID;
	
	private String name;
	
	private long parentFolderID;
	
	public static DocBoxFolder fromEntity(Entity entity) {
		return null;
	}
	
	public Entity toEntity() {
		return null;
	}
	
	// Usual accessor's follow

	public long getFolderID() {
		return folderID;
	}

	public void setFolderID(long folderID) {
		this.folderID = folderID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentFolderID() {
		return parentFolderID;
	}

	public void setParentFolderID(long parentFolderID) {
		this.parentFolderID = parentFolderID;
	}

}
