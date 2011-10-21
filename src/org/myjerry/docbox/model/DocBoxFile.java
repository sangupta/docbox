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

package org.myjerry.docbox.model;

import java.util.Date;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Entity;

/**
 * 
 * @author sangupta
 * @created 21 Oct 2011
 */
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
		file.parentFolderID = (Long) entity.getProperty("parentFolderID");
		file.fileID = (Long) entity.getKey().getId();
		
		return file;
	}
	
	public Entity toEntity() {
		Entity entity = new Entity(DocBoxFile.class.getSimpleName());
		
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
