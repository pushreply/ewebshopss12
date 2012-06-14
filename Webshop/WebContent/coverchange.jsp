<%@page import="shop.dto.DBAlbum"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Album anzeigen" />
</c:import>

<h1>${album.albumTitel}</h1>

<!--  <img src="images/bild.jpg" width=120 height=130 alt="bild"> -->

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

<form name="Formular" action="controller" method="post"
		enctype="multipart/form-data" onSubmit="return validate(this,var_1)">
		<table>
			<tr>
				<td class="table-label">Cover:</td>
				<td><input type="file" name="coverpage" value="Datei auswählen"
					type="text">
				</td>
			</tr>
			<tr>
				<td class="table-label"></td>
				<td>
				<input type="hidden" name="albumid" value="${album.identifier}">
				<input type="submit" name="senden" value="coverchange"
					onClick="showHint()"
					onclick="return validate(this.form,var_1,msg_1)">
				</td>
			</tr>
		</table>
</form>
</html>