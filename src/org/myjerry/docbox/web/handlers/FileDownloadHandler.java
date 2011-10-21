package org.myjerry.docbox.web.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.docbox.BeanNames;
import org.myjerry.docbox.Beans;
import org.myjerry.docbox.model.DocBoxFile;
import org.myjerry.docbox.service.FileService;
import org.myjerry.docbox.web.ModelAndView;
import org.myjerry.docbox.web.RequestHandler;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class FileDownloadHandler implements RequestHandler {
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	private FileService fileService = Beans.getBean(BeanNames.FILE_SERVICE);

	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long fileID = Long.parseLong(request.getParameter("file"));
		Long folderID = Long.parseLong(request.getParameter("folder"));
		
		DocBoxFile file = this.fileService.getFileDetails(fileID);
		if(file != null && file.getParentFolderID() == folderID) {
			// allowed to be downloaded
			BlobKey blobKey = file.getBlobKey();
			this.blobstoreService.serve(blobKey, response);
			
			return null;
		}
		
		response.sendRedirect("/index.html?folder=" + folderID);
		return null;
	}

}
