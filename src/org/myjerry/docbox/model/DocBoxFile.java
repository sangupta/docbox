package org.myjerry.docbox.model;

import java.util.Date;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DocBoxFile {
	
	private long fileID;
	
	private String name;
	
	private String contentType;
	
	private String md5;
	
	private BlobKey blobKey;
	
	private long size;
	
	private Date creationDate;
	
	private long parentFolderID;
	
	public DocBoxFile() {
		// do nothing
	}
	
	public DocBoxFile(BlobInfo blobInfo) {
		this.name = blobInfo.getFilename();
		this.contentType = blobInfo.getContentType();
		this.md5 = blobInfo.getMd5Hash();
		this.size = blobInfo.getSize();
		this.creationDate = blobInfo.getCreation();
		this.blobKey = blobInfo.getBlobKey();
	}

	public static DocBoxFile fromEntity(Entity entity) {
		DocBoxFile file = new DocBoxFile();
		
		file.name = (String) entity.getProperty("name");
		file.contentType = (String) entity.getProperty("contentType");
		file.md5 = (String) entity.getProperty("md5");
		file.size = (Long) entity.getProperty("size");
		file.creationDate = (Date) entity.getProperty("creationDate");
		file.blobKey = (BlobKey) entity.getProperty("blobKey");
		file.name = (String) entity.getProperty("name");
		file.fileID = (Long) entity.getKey().getId();
		
		return file;
	}
	
	public Entity toEntity() {
		Key key = KeyFactory.createKey(DocBoxFile.class.getSimpleName(), this.name);
		Entity entity = new Entity(DocBoxFile.class.getSimpleName(), key);
		
		entity.setProperty("name", this.name);
		entity.setProperty("contentType", this.contentType);
		entity.setProperty("md5", this.md5);
		entity.setProperty("blobKey", this.blobKey);
		entity.setProperty("size", this.size);
		entity.setProperty("creationDate", this.creationDate);
		entity.setProperty("parentFolderID", this.parentFolderID);
		
		return entity;
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public BlobKey getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(BlobKey blobKey) {
		this.blobKey = blobKey;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public long getFileID() {
		return fileID;
	}

}
