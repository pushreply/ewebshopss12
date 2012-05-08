<%@page import="shop.dto.DBAlbum"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Album anzeigen" />
</c:import>

<h3>Das Album ${album.albumTitel}</h3>

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


<table>
	<tr>
		<td>Title:</td>
		<td>${album.albumTitel}</td>
	</tr>
	<tr>
		<td>Artist:</td>
		<td>${album.artist}</td>
	</tr>
	<tr>
		<td>Disk Anzahl:</td>
		<td>${album.numberOfDisks}</td>
	</tr>
	<tr>
		<td>Preis:</td>
		<td>${album.price}</td>
	</tr>
	<tr>
		<td>Anzahl:</td>
		<td>${album.amount}</td>
	</tr>
	<tr>
		<td>Anzahl der Tracks:</td>
		<td>${album.numberOfTracks}</td>
	</tr>
	<tr>
		<td>Label:</td>
		<td>${album.label}</td>
	</tr>

	<tr>
		<td><c:choose>
				<c:when test="${isAdmin=='true'}">
					<form action="controller" method="get">
						<input type="hidden" name="action" value="albumInFelder">
						<input type="hidden" name="uuid1" value="${album.identifier}">
						<input type="submit" value="Bearbeiten">
					</form>
				</c:when>
			</c:choose></td>
		<td><form action="controller" method="post">
				<input type="hidden" name="action" value="albumTracks"> <input
					type="hidden" name="uuid2" value="${album.identifier}"> <input
					type="submit" value="Tracks anzeigen">
			</form></td>
		<td><c:choose>
				<c:when test="${isAdmin=='true'}">
					<form action="controller" method="post">
						<input type="hidden" name="action" value="album"> <input
							type="hidden" name="delete" value="${album.identifier}">
						<input type="submit" value="Album l&ouml;schen">
					</form>
				</c:when>
			</c:choose></td>
	</tr>
</table>

<!--Tracks eines Albums -->
<c:forEach var="currentDiskNumber" begin="1" end="${album.numberOfDisks}">
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
								value="Play">
						</form>
					</td>
					<td><c:choose>
							<c:when test="${isAdmin=='true'}">
								<form action="controller" method="get">
									<input type="hidden" name="action" value="editieren"> <input
										type="hidden" name="identifier" value="${track.identifier}">
									<input type="submit" value="editieren">
								</form>
							</c:when>
						</c:choose></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</c:forEach>

<c:import url="footer.jsp" />