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

<ul>
	<li class="label">Title: ${album.albumTitel}</li>
	<li><div class="label">Artist:</div> ${album.artist}
	<li><div class="label">Disk Anzahl:</div> ${album.numberOfDisks}</li>
	<li><div class="label">Preis:</div> ${album.price}</li>
	<li><div class="label">Anzahl:</div> ${album.amount}</li>
	<li><div class="label">Anzahl der Tracks:</div>${album.numberOfTracks}</li>
	<li><div class="label">Label:</div> ${album.label}</li>
	<c:if test="${isAdmin=='true'}">
		<li><form action="controller" method="get">
				<input type="hidden" name="action" value="album"> <input
					type="hidden" name="changeAlbumInfo" value="${album.identifier}">
				<input type="submit" value="Bearbeiten">
			</form></li>
			
		<li><form action="controller" method="post">
				<input type="hidden" name="action" value="album"> <input
					type="hidden" name="delete" value="${album.identifier}"> <input
					type="submit" value="Album l&ouml;schen">
			</form></li>
	</c:if>
	<li><form action="controller" method="get">
				<input type="hidden" name="action" value="orderalbum"> 
				<input type="hidden" name="albumID" value="${album.identifier}">
				<input type="submit" value="In den Warenkorb legen ">
			</form></li>
</ul>

<c:if test="${isAdmin=='true'}">
	<h2>Neue Tracks hinzufügen</h2>
	<form action="controller" method="post" enctype="multipart/form-data">
		<input type="hidden" name="identifier" value="${album.identifier}">
		<input type="file" name="file" id="file"> <input type=submit
			value='Upload File'>
	</form>
</c:if>
<!--Tracks eines Albums -->
<c:forEach var="currentDiskNumber" begin="1"
	end="${album.numberOfDisks}">
	<h2>
		<c:out value="${currentDiskNumber}" />
		.Disk
	</h2>
	<table>
		<tr>
			<th>Track Number</th>
			<th>Title</th>
			<th>Artist</th>
			<th>Year</th>
			<th>Play</th>
			<c:choose>
				<c:when test="${isAdmin=='true'}">
					<th>editieren</th>
				</c:when>
			</c:choose>
		</tr>
		<c:forEach items="${album.tracks}" var="track">
			<c:if test="${track.trackDiskNumber eq currentDiskNumber}">
				<tr>
					<td>${track.trackNumber}</td>
					<td>${track.trackTitle}</td>
					<td>${track.trackArtist}</td>
					<td>${track.trackDate}</td>
					<td>
						<form action="loadtrack" method="get">
							<input type="hidden" name="identifier"
								value="${track.identifier}"> <input type="submit"
								value="Abspielen">
						</form>
					</td>
					<c:if test="${isAdmin=='true'}">
						<td>
							<form action="controller" method="get">
								<input type="hidden" name="action" value="track"> <input
									type="hidden" name="changeTrackData"
									value="${track.identifier}"> <input type="hidden"
									name="albumIdentifier" value="${album.identifier}"> <input
									type="submit" value="Editieren">
							</form>
						</td>
					</c:if>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</c:forEach>

<c:import url="footer.jsp" />