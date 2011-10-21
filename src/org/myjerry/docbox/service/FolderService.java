package org.myjerry.docbox.service;

import java.util.List;

import org.myjerry.docbox.model.DocBoxFolder;

public interface FolderService {
	
	public List<DocBoxFolder> getRootFolder();
	
	public List<DocBoxFolder> getChildFolders(long folderID);

}
