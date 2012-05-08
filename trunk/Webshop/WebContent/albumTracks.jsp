<%@page import="shop.dto.DBAlbum"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Tracks eines Albums" />
</c:import>

<c:forEach var="i" begin ="1" end="${albumTracks.numberOfDisks}">
    <h2><c:out value ="${i}" />.Disk</h2>
	<table>
		<tr>
		<th>Track Number</th>
		<th>Title</th>
		<th>Artist</th>
		<th>Year</th>
		<th>Play</th>
		<th>editieren</th>
		</tr>
		<c:forEach items="${albumTracks.tracks}" var="track">
			<tr>
				<td>${track.trackNumber}</td>
				<td>${track.trackTitle}</td>
				<td>${track.trackArtist}</td>	
				<td>${track.trackDate}</td>
				<td>
				   <form action="loadtrack" method="get">
						<input type="hidden"  name="identifier" value="${track.identifier}">
						<input type="submit"  value="Play">
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
		</c:forEach>
	</table>
</c:forEach>	
<c:import url="footer.jsp" />