<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Webshop Startseite" />
</c:import>
<p>
	<b>Webshop Startseite</b>
<p>
<p>Men�</p>

<br>
<br>
<!-- <input type="submit" name="albumHochladenButton" value="Album hochladen"> <br>	
	<input type="submit" name="suchenButton" value="Suchen"> <br>
 -->

<form action="controller" method="post">
	<!-- Controller-Servlet wird aufgerufen und entscheidet, wohin geleitet wird -->
	<input type="hidden" name="action" value="track"> <input
		type="submit" name="upload" value="Track hochladen"> <br>
	<input type="submit" name="show" value="Tracks anzeigen"> <br>
</form>

<form action="controller" name="controlleraction" method="post">
	<input type="submit" name="trackEditieren" value="Track editieren">
	<br>

</form>

<form action="controller" method="post">
	<input type="hidden" name="action" value="category"> <input
		type="submit" value="Kategorien verwalten">
</form>

<form action="controller" method="post">
	<input type="hidden" name="action" value="keyword"> <input
		type="submit" value="Schl�sselworte verwalten">
</form>

<c:import url="footer.jsp" />