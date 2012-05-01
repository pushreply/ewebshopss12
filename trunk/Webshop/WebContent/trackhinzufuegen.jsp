<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Lied hinzufügen" />
</c:import>

<h3>Album</h3>

<p><b>${album.albumTitel}</b> von <b>${album.artist}</b></p>

<table width="589" border="1">
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
						<input type="hidden"  name="identifier" value="${track.identifier}">
						<input type="submit"  value="Play">
					</form></td>
				<td><form action="controller" method="post">
				        <input type="hidden"  name="action" value="editieren">
						<input type="hidden"  name="identifier" value="${track.identifier}">
						<input type="submit"  value="editieren">
					</form></td>
			</tr>
		</c:forEach>
	</table>

 <form action="controller"  		
 		method="post"
 		enctype="multipart/form-data">
 		<table border=1>
  <tr><td>Titel:</td><td><c:out value="" /></td></tr>
  <tr><td>Artist:</td><td><c:out value="" /></td></tr>
  <tr><td>Year:</td><td><c:out value="" /></td></tr>
  <tr><td>Genre</td><td><c:out value="" /></td></tr>
  <tr><td>Track Number:</td><td><c:out value="" /></td></tr>
 <!--  <tr><td>Album Cover:</td><td><c:out value="" /></td></tr>-->
 
  <h3>Tracks Hinzufügen</h3>

	<input type="hidden"  name="identifier" value="${album.identifier}"> 	
 	</table>
   		<tr>
     		<td align="right">Datei:</td>
    		<td><input type="file" name="file" id="file"></td>
   		</tr>
	 </table>

	 <input type=submit name="uploadFileSubmitButton" value='Upload File'> 
	</form>
</table>

<c:import url="footer.jsp" />