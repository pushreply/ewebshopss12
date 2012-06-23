<%@page import="shop.dto.DBAlbum"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Album anzeigen" />
</c:import>

<h1>${album.albumTitel}</h1>

<div id="albumpage-top">
	<div id="album-image">
		<c:if test="${empty album.cover}">
			<img src="images/bild.jpg" width=160 height=160 alt="bild"
				alt="Kein Bild vorhanden" title="Kein Bild vorhanden">
		</c:if>
		<c:if test="${ not empty album.cover}">
			<img
				src="imageDisplayProcess?identifier=<c:out value="${album.identifier}"/>"
				width=160 height=160 alt="${album.albumTitel} - ${album.artist}"
				title="${album.albumTitel} - ${album.artist}" />
		</c:if>
	</div>
	<ul id="album-info">
		<li><div class="albumlabel">Title:</div> ${album.albumTitel}</li>
		<li><div class="albumlabel">Artist:</div> ${album.artist}
		<li><div class="albumlabel">Disk Anzahl:</div> ${album.numberOfDisks}</li>
		<li><div class="albumlabel">Preis:</div> ${album.price}</li>
	<li><div class="albumlabel">Lagerbestand:</div> ${album.amount}</li>
	<li><div class="albumlabel">Anzahl der Tracks:</div>${album.numberOfTracks}</li>
	<li><div class="albumlabel">Label:</div> ${album.label}</li>
	<li><div class="albumlabel">Kategorien:</div>
<!-- 		<ul class="album-sublist"> -->
			<c:forEach items="${album.categories}" var="category">
				<li><c:out value="${category.categoryName}" /></li>
			</c:forEach>
<!-- 		</ul></li> -->
<!-- 		<li> -->
	
		<div class="albumlabel">Schlagwörter:</div>
<!-- 		<ul class="album-sublist"> -->
			<c:forEach items="${album.keywords}" var="keyword">
				<c:out value="${keyword.keywordName}" />
			</c:forEach>
<!-- 		</ul></li> -->
</ul>
</div>
<ul id="album-features">
	<c:if test="${isAdmin=='true'}">
		<li><form action="controller" method="get">
				<input type="hidden" name="action" value="album"> <input
					type="hidden" name="changeAlbumInfo" value="${album.identifier}">
				<input  class="userbutton"  type="submit" value="Bearbeiten">
			</form></li>

		<li><form action="controller" method="post">
				<input type="hidden" name="action" value="album"> <input
					type="hidden" name="delete" value="${album.identifier}"> <input
					 class="delbutton" type="submit" value="Album l&ouml;schen">
			</form></li>
	</c:if>
	<li><form action="controller" method="get">
			<input type="text" name="anzahl" value="1"> <input
				type="hidden" name="action" value="orderalbum"> <input
				type="hidden" name="PutAlbumInSessionID" value="${album.identifier}">
			<input  class="userbutton" type="submit" value="In den Warenkorb">
		</form></li>
</ul>

<div id="albumpage-tracks">
<c:if test="${isAdmin=='true'}">
	<h2>Neue Tracks hinzufügen</h2>
	<form action="controller" method="post" enctype="multipart/form-data">
		<input type="hidden" name="identifier" value="${album.identifier}">
		<input type="file" name="file" id="file"> <input class="userbutton" type=submit
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
								value="${track.identifier}"> <input class="userbutton" type="submit"
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
									 class="userbutton" type="submit" value="Editieren">
							</form>
						</td>
					</c:if>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</c:forEach>
</div>

<c:import url="footer.jsp" />