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

import com.google.appengine.api.datastore.Entity;

/**
 * 
 * @author sangupta
 * @created 21 Oct 2011
 */
public class DocBoxFolder {
	
	public static long ROOT_FOLDER_ID = 0;
	
	private long folderID;
	
	private String name;
	
	private Date creationDate;
	
	private long parentFolderID;
	
	public static DocBoxFolder fromEntity(Entity entity) {
		DocBoxFolder folder = new DocBoxFolder();
		
		folder.name = (String) entity.getProperty("name");
		folder.parentFolderID = (Long) entity.getProperty("parentFolderID");
		folder.creationDate = (Date) entity.getProperty("creationDate");
		folder.folderID = (Long) entity.getKey().getId();
		
		return folder;
	}
	
	public Entity toEntity() {
		Entity entity = new Entity(DocBoxFolder.class.getSimpleName());
		
		entity.setProperty("name", this.name);
		entity.setProperty("parentFolderID", this.parentFolderID);
		entity.setProperty("creationDate", this.creationDate);
		
		return entity;
	}
	
	// Usual accessor's follow

	public long getFolderID() {
		return folderID;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
