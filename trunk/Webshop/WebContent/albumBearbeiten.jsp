<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Album bearbeiten" />
</c:import>



<h3>
	Album
	<c:out value="${album.albumTitel}" default="N/A" />
	bearbeiten
</h3>

<img src="images/bild.jpg" width=120 height=130 alt="bild">

<form name="Formular" action="controller" method="get" enctype="multipart/form-data" onsubmit="checkAlbumInput()">

	<ul>
		<li><div class="label">Titel:</div> <input type="text"
			name="titel"
			value="<c:out value="${album.albumTitel}"  default="N/A" />" /></li>
		<li><div class="label">Artist:</div> <input type="text"
			name="artist"
			value="<c:out value="${album.artist}"  default="N/A" />" /></li>
		<li><div class="label">Anzahl der Disks:</div> <input type="text"
			name="diskAnzahl"
			value="<c:out value="${album.numberOfDisks}"  default="N/A" />" /></li>
		<li><div class="label">Preis:</div> <input type="text"
			name="preis" value="<c:out value="${album.price}"  default="N/A" />" /></li>
		<li><div class="label">Anzahl:</div> <input type="text"
			name="anzahl"
			value="<c:out value="${album.amount}"  default="N/A" />" /></li>
		<li><div class="label">Anzahl der Tracks:</div> <input
			type="text" name="trackAnzahl"
			value="<c:out value="${album.numberOfTracks}"  default="N/A" />" /></li>
		<li><div class="label">Label:</div> <input type="text"
			name="label" value="<c:out value="${album.label}"  default="N/A" />" /></li>

		<li>
			<div class="label">Kategorie:</div>
			<ul class="bulletless-list">
				<c:forEach items="${categories}" var="category">
					<li><input type="checkbox" id="{category.categoryName}"
						name="category" value="${category.identifier}"
						${fn:contains(parts.category, '{category.identifier}') ? 'categoried' : ''}>
						<c:out value="${category.categoryName}" /></li>
				</c:forEach>
			</ul>
		</li>
		<li>
			<div class="label">Schlagw&ouml;rter:</div>
			<ul class="bulletless-list">
				<c:forEach items="${keywordies}" var="keyword">
					<li><input type="checkbox" id="{keyword.keywordName}"
						name="keyword" value="${keyword.identifier}"
						${fn:contains(parts.keyword, '{keyword.identifier}') ? 'keywordied' : ''}>
						<c:out value="${keyword.keywordName}" /></li>
				</c:forEach>
			</ul>
		</li>
		<li>
			<div class="label">Cover:</div> <input type="file" name="coverpage"
			value="Datei auswählen" type="text">
		</li>

	</ul>

	<input type="hidden" name="action" value="album"> 
	<input type="hidden" name="updateAlbum" value="<c:out value="${album.identifier}"/>" /> 
	<input type="submit" value="Speichern">
</form>

<c:import url="footer.jsp" />