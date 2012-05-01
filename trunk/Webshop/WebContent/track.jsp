<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Lied Abspielen" />
</c:import>

	<table width="589" border="1">
		<tr>
		<th>Track Number</th>
		<th>Title</th>
		<th>Artist</th>
		<th>Year</th>
		<th>Play</th>
		<th>editieren</th>
		</tr>
		<c:forEach items="${AlbumTracks}" var="track">
			<tr>
				<td>${track.trackNumber}</td>
				<td>${track.trackTitle}</td>
				<td>${track.trackArtist}</td>
				<td>${track.trackDate}</td>
				<td><form action="loadtrack" method="get">
						<input type="hidden"  name="trackID" value="${track.identifier}">
						<input type="submit"  value="Play">
					</form></td>
				<td><form action="controller" method="get">
				        <input type="hidden"  name="action">
						<input type="hidden"  name="uuid" value="${track.identifier}">
						<input type="submit"  value="editieren">
					</form></td>
			</tr>
		</c:forEach>
	</table>
<c:import url="footer.jsp" />