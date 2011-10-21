package org.myjerry.docbox.service;

import java.util.List;

import org.myjerry.docbox.model.DocBoxFile;

public interface FileService {
	
	public List<DocBoxFile> getFilesInFolder(long folderID);

	public DocBoxFile getFileDetails(Long fileID);
	
}
