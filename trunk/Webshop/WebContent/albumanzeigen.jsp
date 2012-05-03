<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Album anzeigen" />
</c:import>

<h3>Das Album ${album.albumTitel}<h3>

 <img src="images/bild.jpg" width=120 height=130 alt="bild">
 
 <table >
		<tr>
		   <td>Title:</td><th>${album.albumTitel}</td>
		</tr>
		<tr>
		   <td>Artist:</td><td>${album.artist}</td>
		</tr>
		<tr>
		   <td>Disk Anzahl:</td><td>${album.numberOfDisks}</td>
		</tr>
		<tr>
		   <td>Preis:</td><td>${album.price}</td>
		</tr>
		<tr>
		   <td>Anzahl:</td><td>${album.amount}</td>
		</tr>
		<tr>
		   <td>Anzahl der Tracks:</td><td>${album.numberOfTracks}</td>
		</tr>
		<tr>
		   <td>Label:</td><td>${album.label}</td>
		</tr>
		
		<tr>
				<td><form action="controller" method="post">
				        <input type="hidden"  name="action" value="albumEditieren">
						<input type="hidden"  name="identifier" value="${album.identifier}">
						<input type="submit"  value="editieren">
					</form></td>
		</tr>
	</table>
<c:import url="footer.jsp" />