<!-- contains all urls, include this file if you need any -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:url var="home" value="index.jsp">
		<c:param name="action" value="keyword" />
	</c:url>
	
	<c:url var="trackUpload" value="controller">
		<c:param name="action" value="track" />
		<c:param name="upload" value="true" />
	</c:url>
	
	<c:url var="trackShow" value="controller">
		<c:param name="action" value="track" />
		<c:param name="show" value="true" />
	</c:url>
	
	<c:url var="keyword" value="controller">
		<c:param name="action" value="keyword" />
	</c:url>
	
	<c:url var="category" value="controller">
		<c:param name="action" value="category" />
	</c:url>
	
	<c:url var="login" value="controller">
		<c:param name="action" value="login" />
	</c:url>
	
	<c:url var="register" value="controller">
		<c:param name="action" value="register" />
	</c:url>
	
	<c:url var="alben" value="controller">
		<c:param name="action" value="alben" />
	</c:url>