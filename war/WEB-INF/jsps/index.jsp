<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<a href="/_ah/admin" target="_blank" >Local Datastore</a>

<h1>Doc Box</h1>

<h3>File/Folder List</h3>
<c:if test="${not empty folders}">
	<ul>
		<c:forEach items="${folders}" var="folder">
			<li>${folder.name}</li>
		</c:forEach>
	</ul>
</c:if>
<c:if test="${not empty files}">
	<ul>
		<c:forEach items="${files}" var="file">
			<li>${file.name}</li>
		</c:forEach>
	</ul>
</c:if>

<h3>Add Folder</h3>
Click <a href="">here</a> to add a new folder.

<h3>Upload File</h3>
<form action="<%= blobstoreService.createUploadUrl("/uploadFile.html") %>" method="post" enctype="multipart/form-data">
	<input type="hidden" name="currentFolderID" value="0" /> 
	<input type="file" name="fileBeingUploaded">
	<input type="submit" value="Upload File">
</form>
