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

<form action="controller" method="get" enctype="multipart/form-data">

	<ul>
		<li><div class="label">Titel:</div> <input type="text"
			name="titel" size="30" maxlength="30"
			value="<c:out value="${album.albumTitel}"  default="N/A" />" /></li>
		<li><div class="label">Artist:</div> <input type="text"
			name="artist" size="30" maxlength="40"
			value="<c:out value="${album.artist}"  default="N/A" />" /></li>
		<li><div class="label">Anzahl der Disks:</div> <input type="text"
			name="diskAnzahl" size="30" maxlength="40"
			value="<c:out value="${album.numberOfDisks}"  default="N/A" />" /></li>
		<li><div class="label">Preis:</div> <input type="text"
			name="preis" size="30" maxlength="40"
			value="<c:out value="${album.price}"  default="N/A" />" /></li>
		<li><div class="label">Anzahl:</div> <input type="text"
			name="anzahl" size="30" maxlength="40"
			value="<c:out value="${album.amount}"  default="N/A" />" /></li>
		<li><div class="label">Anzahl der Tracks:</div> <input
			type="text" name="trackAnzahl" size="30" maxlength="40"
			value="<c:out value="${album.numberOfTracks}"  default="N/A" />" /></li>
		<li><div class="label">Label:</div> <input type="text"
			name="label" size="30" maxlength="40"
			value="<c:out value="${album.label}"  default="N/A" />" /></li>
		<li>&nbsp;</li>
		<li><input type="hidden" name="action" value="albumBearbeiten">
			<input type="submit" value="Speichern"></li>
	</ul>
	<input type="hidden" name="ident1" size="30" maxlength="30"
		value="<c:out value="${album.identifier}"/>" />
</form>

<c:import url="footer.jsp" />