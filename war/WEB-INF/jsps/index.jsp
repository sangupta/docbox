<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
	<head>
		<title>Doc Box - The personal online file store.</title>
	</head>
	<body>
		<a href="/_ah/admin" target="_blank" >Local Datastore</a>
		
		<h1>Doc Box</h1>
		
		<h3>File/Folder List</h3>
		<table>
			<tr>
				<th>Name</th>
				<th>Created</th>
				<th>Size</th>
			</tr>
			
			<c:if test="${not empty currentFolder}">
				<tr>
					<td><a href="/index.html?folder=${currentFolder.parentFolderID}">..</a></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</c:if>
		
			<c:if test="${not empty folders}">
				<c:forEach items="${folders}" var="folder">
					<tr>
						<td><a href="/index.html?folder=${folder.folderID}">${folder.name}</a></td>
						<td>${folder.creationDate}</td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${not empty files}">
				<c:forEach items="${files}" var="file">
					<tr>
						<td><a href="/fileDetails.html?file=${file.fileID}">${file.name}</a></td>
						<td>${file.creationDate}</td>
						<td>${file.size}</td>
					</tr>
				</c:forEach>
			</c:if>
		
		</table>
		
		<h3>Add Folder</h3>
		<form action="/createFolder.html" method="POST">
			<input type="hidden" name="currentFolderID" value="${currentFolderID}" />
			<input type="text" name="folderName" maxlength="100" />
			<input type="submit" value="Create Folder" />
		</form>
		
		<h3>Upload File</h3>
		<form action="<%= blobstoreService.createUploadUrl("/uploadFile.html") %>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="currentFolderID" value="${currentFolderID}" /> 
			<input type="file" name="fileBeingUploaded">
			<input type="submit" value="Upload File">
		</form>
	</body>
</html>
