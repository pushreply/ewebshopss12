<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Webshop Startseite" />
</c:import>
<h1>Webshop Startseite</h1>
<p>Menü</p>

<br>
<br>
<!-- <input type="submit" name="albumHochladenButton" value="Album hochladen"> <br>	
	<input type="submit" name="suchenButton" value="Suchen"> <br>
 -->

<form action="controller" method="post">
	<!-- Controller-Servlet wird aufgerufen und entscheidet, wohin geleitet wird -->
	<input type="hidden" name="logout" value="track"> 
	<input type="submit" name="upload" value="Track hochladen"> <br>
	<input type="submit" name="show" value="Tracks anzeigen"> <br>
</form>

<form action="controller" method="post">
	<input type="hidden" name="action" value="album">
	<input type="submit" name="upload" value="Album hochladen"> <br>
	<input type="submit" name="showalbum" value="Album anzeigen"> <br>
</form>

<form action="controller" name="controlleraction" method="post">
    <input type="hidden" name="action" value="trackEditieren">
	<input type="submit" value="Track editieren">
	<br>

</form>

<form action="controller" method="post">
	<input type="hidden" name="action" value="category"> <input
		type="submit" value="Kategorien verwalten">
</form>

<form action="controller" method="post">
	<input type="hidden" name="action" value="keyword"> <input
		type="submit" value="Schlüsselworte verwalten">
</form>

<c:import url="footer.jsp" />