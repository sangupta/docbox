package org.myjerry.docbox.web.handlers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myjerry.docbox.model.DocBoxFile;
import org.myjerry.docbox.web.ModelAndView;
import org.myjerry.docbox.web.RequestHandler;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class FileUploadHandler implements RequestHandler {
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	private BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
	
	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
        BlobKey blobKey = blobs.get("fileBeingUploaded");
        
        if (blobKey == null) {
            throw new RuntimeException("Blob not uploaded.");
        }

        BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
        
        DocBoxFile file = new DocBoxFile(blobInfo);
        long folderID = Long.parseLong(request.getParameter("currentFolderID"));
        file.setParentFolderID(folderID);
        
        datastore.put(file.toEntity());
        
        response.sendRedirect("/index.html?folder=" + folderID);
        return null;
	}

}
