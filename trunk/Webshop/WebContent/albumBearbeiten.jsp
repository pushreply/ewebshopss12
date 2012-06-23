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

<div id="albumpage-top">
	<div id="album-image">
	<c:if test="${empty album.cover}">
		<img src="images/bild.jpg" width=120 height=130 alt="bild"
			alt="Kein Bild vorhanden" title="Kein Bild vorhanden">
	</c:if>
	<c:if test="${ not empty album.cover}">
	<img
		src="imageDisplayProcess?identifier=<c:out value="${album.identifier}"/>"
		width=160 height=160 alt="${album.albumTitel} - ${album.artist}"
		title="${album.albumTitel} - ${album.artist}" />
	</c:if>
	</div>
</div>	

<form name="Formular" action="controller" method="get"
	enctype="multipart/form-data" onSubmit="return validate(this,var_1)">
	<table>
	<tr>
		<td>
		<ul>
			<li><div class="albumlabel">Titel:</div> <input type="text"
				name="titel"
				value="<c:out value="${album.albumTitel}"  default="N/A" />" /></li>
			<li><div class="albumlabel">Artist:</div> <input type="text"
				name="artist"
				value="<c:out value="${album.artist}"  default="N/A" />" /></li>
			<li><div class="albumlabel">Preis:</div> <input type="text"
				name="preis" value="<c:out value="${album.price}"  default="N/A" />" /></li>
			<li><div class="albumlabel">Label:</div> <input type="text"
				name="label" value="<c:out value="${album.label}"  default="N/A" />" /></li>
			<li><div class="albumlabel">Anzahl der Tracks:</div> <input
				type="text" name="trackAnzahl"
				value="<c:out value="${album.numberOfTracks}"  default="N/A" />" /></li>
			<li><div class="albumlabel">Anzahl der Disks:</div> <input type="text"
				name="diskAnzahl"
				value="<c:out value="${album.numberOfDisks}"  default="N/A" />" /></li>
			<li><div class="albumlabel">Lagerbestand:</div> <input type="text"
				name="albumAnzahl"
				value="<c:out value="${album.amount}"  default="N/A" />" /></li>
			</ul>
			</td>
		</tr>
		<tr>
			<td>	
			<fieldset>
			<legend>Kategorie:</legend>	
				<ul class="bulletless-list">
					<c:forEach items="${categories}" var="category">
						<li><input type="checkbox" id="{category.key.indentifier}"
							value="${category.key.identifier}"
							name="category"
							<c:if test="${category.value}">checked</c:if> >
							<c:out value="${category.key.categoryName}" /></li>
					</c:forEach>
				</ul>
			</fieldset>
			</td>
			<td>
			<fieldset>
			<legend>Schlagw&ouml;rter:</legend>	
				<ul class="bulletless-list">
					<c:forEach items="${keywordies}" var="keyword">
						<li><input type="checkbox" id="${keyword.key.identifier}"
							value="${keyword.key.identifier}"
							name="keyword"
							<c:if test="${keyword.value}">checked</c:if> >
							
							<c:out value="${keyword.key.keywordName}" /></li>
					</c:forEach>
				</ul>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>
			<ul>
			<li>
				<input type="hidden" name="action" value="album"> 
				<input type="hidden" name="updateAlbum" value="<c:out value="${album.identifier}"/>" />
				<input class="userbutton" type="submit" name="coverchange" value="Cover &auml;ndern" onclick="return validate(this.form,var_1,msg_1)">
			</li>
	
			<li>	
				<input type="hidden" name="action" value="album">
				<input type="hidden" name="updateAlbum" value="<c:out value="${album.identifier}"/>" /> 
				<input class="userbutton" type="submit" value="Speichern" onclick="return validate(this.form,var_1,msg_1)">
			</li></ul>
			</td>
		</tr>

	
</table>
	</form>
<c:import url="footer.jsp" />