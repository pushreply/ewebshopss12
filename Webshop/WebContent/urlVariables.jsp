<!-- contains all urls, include this file if you need any -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="home" value="controller">
	<c:param name="action" value="home" />
</c:url>

<c:url var="trackUpload" value="controller">
	<c:param name="action" value="album" />
	<c:param name="upload" value="true" />
</c:url>

<c:url var="trackShow" value="controller">
	<c:param name="action" value="album" />
	<c:param name="show" value="all" />
</c:url>

<c:url var="keyword" value="controller">
	<c:param name="action" value="keyword" />
</c:url>

<c:url var="category" value="controller">
	<c:param name="action" value="category" />
</c:url>

<c:url var="order" value="controller">
	<c:param name="action" value="orderalbum" />
	<c:param name="warenkorb" value="show" />
</c:url>

<c:url var="login" value="controller">
	<c:param name="action" value="login" />
</c:url>

<c:url var="logout" value="controller">
	<c:param name="action" value="logout" />
</c:url>

<c:url var="register" value="controller">
	<c:param name="action" value="register" />
</c:url>

<c:url var="alben" value="controller">
	<c:param name="action" value="album" />
	<c:param name="show" value="all" />
</c:url>

<c:url var="uploadAlbum" value="controller">
	<c:param name="action" value="album" />
	<c:param name="upload" value="true" />
</c:url>

<c:url var="simplesearch" value="controller">
	<c:param name="action" value="albumsearch" />
	<c:param name="upload" value="true" />
</c:url>

<c:url var="customer" value="controller">
	<c:param name="action" value="customer" />
	<c:param name="show" value="profile" />
</c:url>

<c:url var="address" value="controller">
	<c:param name="action" value="address" />

</c:url>


