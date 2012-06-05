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

<c:if test="${empty album.cover}">
	<img src="images/bild.jpg" width=120 height=130 alt="bild"
		alt="Kein Bild vorhanden" title="Kein Bild vorhanden">
</c:if>
<c:if test="${ not empty album.cover}">
	<img
		src="imageDisplayProcess?identifier=<c:out value="${album.identifier}"/>"
		width=120 height=130 alt="${album.albumTitel} - ${album.artist}"
		title="${album.albumTitel} - ${album.artist}" />
</c:if>

<form name="Formular" action="controller" method="get"
	enctype="multipart/form-data" onSubmit="return validate(this,var_1)">

	<ul>
		<li><div class="label">Titel:</div> <input type="text"
			name="titel"
			value="<c:out value="${album.albumTitel}"  default="N/A" />" /></li>
		<li><div class="label">Artist:</div> <input type="text"
			name="artist"
			value="<c:out value="${album.artist}"  default="N/A" />" /></li>
		<li><div class="label">Preis:</div> <input type="text"
			name="preis" value="<c:out value="${album.price}"  default="N/A" />" /></li>
		<li><div class="label">Label:</div> <input type="text"
			name="label" value="<c:out value="${album.label}"  default="N/A" />" /></li>
		<li><div class="label">Anzahl der Tracks:</div> <input
			type="text" name="trackAnzahl"
			value="<c:out value="${album.numberOfTracks}"  default="N/A" />" /></li>
		<li><div class="label">Anzahl der Disks:</div> <input type="text"
			name="diskAnzahl"
			value="<c:out value="${album.numberOfDisks}"  default="N/A" />" /></li>
		<li><div class="label">Anzahl:</div> <input type="text"
			name="albumAnzahl"
			value="<c:out value="${album.amount}"  default="N/A" />" /></li>
		<li>
			<div class="label">Kategorie:</div>
			<ul class="bulletless-list">
				<c:forEach items="${categories}" var="category">
					<li><input type="checkbox" id="{category.key.indentifier}"
						value="${category.key.identifier}"
						name="category"
						<c:if test="${category.value}">checked</c:if> >
						<c:out value="${category.key.categoryName}" /></li>
				</c:forEach>
			</ul>
		</li>
		<li>
			<div class="label">Schlagw&ouml;rter:</div>
			<ul class="bulletless-list">
				<c:forEach items="${keywordies}" var="keyword">
					<li><input type="checkbox" id="${keyword.key.identifier}"
						value="${keyword.key.identifier}"
						name="keyword"
						<c:if test="${keyword.value}">checked</c:if> >
						
						<c:out value="${keyword.key.keywordName}" /></li>
				</c:forEach>
			</ul>
		</li>
		<li>
			<input type="hidden" name="action" value="album"> 
			<input type="hidden" name="updateAlbum" value="<c:out value="${album.identifier}"/>" />
			<input type="submit" name="coverchange" value="Cover &auml;ndern" onclick="return validate(this.form,var_1,msg_1)">
		</li>

	</ul>
<input type="hidden" name="action" value="album">
	<input type="hidden" name="updateAlbum" value="<c:out value="${album.identifier}"/>" /> <input type="submit"
		value="Speichern" onclick="return validate(this.form,var_1,msg_1)">

</form>


<c:import url="footer.jsp" />