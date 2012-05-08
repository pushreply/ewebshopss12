<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Lied hinzufügen" />
</c:import>
<c:choose>
	<c:when test="${isAdmin=='true'}">
		<h1>${album.albumTitel} von ${album.artist}</h1>

		<h2>Neue Tracks hinzufügen</h2>

		<form action="controller" method="post" enctype="multipart/form-data">
			<input type="hidden" name="identifier" value="${album.identifier}">
			<input type="file" name="file" id="file"> <input type=submit
				name="uploadFileSubmitButton" value='Upload File'>
		</form>

		<h2>Tracks Übersicht</h2>
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
				<th>Track Number</th>
				<th>Title</th>
				<th>Artist</th>
				<th>Year</th>
				<th>Play</th>
				<th>editieren</th>
			</tr>
			<c:forEach items="${track}" var="track">
				<tr>
					<td>${track.trackNumber}</td>
					<td>${track.trackTitle}</td>
					<td>${track.trackArtist}</td>
					<td>${track.trackDate}</td>
					<td><form action="loadtrack" method="get">
							<input type="hidden" name="identifier"
								value="${track.identifier}"> <input type="submit"
								value="Play">
						</form></td>
					<td><form action="controller" method="post">
							<input type="hidden" name="action" value="editieren"> <input
								type="hidden" name="identifier" value="${track.identifier}">
							<input type="submit" value="editieren">
						</form></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
</c:choose>
<c:import url="footer.jsp" />