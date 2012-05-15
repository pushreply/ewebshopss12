<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Einfache Suche" />
</c:import>
<head>
<title>Einfach Suche</title>
</head>
<body>
  <h3>Einfache Suche</h3>
  <form action="controller" method="post">
  	<table>
  		<tr>
  			<td>Title:</td>
			<td><input type="text" name="albumtitle" value=""></td>
		</tr>
		<tr>
			<td>Interpreter:</td>
			<td><input type="text" name="albumartist" value=""></td>
		</tr>
		<tr>
			<td>Genre:</td>
			<td><input type="text" name="albumcategory" value=""></td>
		</tr>
		<tr>
			<td>Schlagwort:</td>
			<td><input type="text" name="albumkeyword" value=""></td>
		</tr>
		<tr>
			<td><input type="hidden" name="action" value="albumsearch"></td> 
			<td><input type="submit" value="Suchen"></td>
		<tr>
	</table>
		</form>
</body>
<c:import url="footer.jsp" />
</html>