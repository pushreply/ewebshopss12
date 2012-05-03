<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Album</title>
</head>
<body>
 <img src="images/bild.jpg" width=120 height=130 alt="bild">
 
 <table width="589" border="1">
		<tr>
		<th>Title</th>
		<th>Artist</th>
		<th>Disk Anzahl</th>
		<th>Preis</th>
		<th>Anzahl</th>
		<th>Anzahl der Tracks</th>
		<th>Label</th>
		<th>Cover</th>
		</tr>
		<c:forEach items="${Albums}" var="album">
			<tr>
				<td>${album.albumTitel}</td>
				<td>${album.artist}</td>
				<td>${album.numberOfDisks}</td>
				<td>${album.price}</td>
				<td>${album.amount}</td>
				<td>${album.numberOfTracks}</td>
				<td>${album.label}</td>
				<td><img src="${album.coverpath}" width=120 height=130 alt="bild">
				<td><form action="controller" method="post">
				        <input type="hidden"  name="action" value="editieren">
						<input type="hidden"  name="identifier" value="${album.identifier}">
						<input type="submit"  value="editieren">
					</form></td>
			</tr>
		</c:forEach>
	</table>

  <input type=button onClick="location.href='index.jsp'" value='bearbeiten'>
  <input type=button onClick="location.href='trackhinzufuegen.jsp'" value='Track hinzufügen'>
  <div id="contecnt">
  </div>
</body>
</html>