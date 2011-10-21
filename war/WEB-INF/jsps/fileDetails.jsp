<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
	<head>
		<title>Doc Box - The personal online file store.</title>
	</head>
	<body>
		<a href="/_ah/admin" target="_blank" >Local Datastore</a>
		
		<h1>Doc Box</h1>
		
		<h3>File Details</h3>
		<table>
			<tr>
				<th>Property</th>
				<th>Value</th>
			</tr>
			
			<c:if test="${not empty file}">
				<tr>
					<td>Name</td>
					<td>${file.name}</td>
				</tr>
				<tr>
					<td>Content Type</td>
					<td>${file.contentType}</td>
				</tr>
				<tr>
					<td>MD5 Hash</td>
					<td>${file.md5}</td>
				</tr>
				<tr>
					<td>Size</td>
					<td>${file.size}</td>
				</tr>
				<tr>
					<td>Creation Date</td>
					<td>${file.creationDate}</td>
				</tr>
			</c:if>
		</table>
		
	</body>
</html>
