<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Webshop Startseite" />
</c:import>
<h1>Herzlich Willkommen</h1>
<p>
	auf musik.to !<br /> Auf dieser Seite können Sie sich durch eine
	willkürlich ausgewählte und außerdem sehr geringe Auswahl von mp3s
	wühlen und zu ihrem Warenkorb hinzufügen und herunterladen. Wenn Sie bereits wissen was
	Sie möchten, benutzen Sie am besten gleich die Suche.
</p>

<p>Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi.
	Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, vel
	augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam
	rhoncus.</p>

<p>Sed aliquam ultrices mauris. Integer ante arcu, accumsan a,
	consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus
	ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat
	primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed
	aliquam, nisi quis porttitor congue, elit erat euismod orci, ac</p>


<script type="text/javascript">
	function einblenden(elementname) {
		document.getElementById(elementname).style.display = 'block';
	}

	function ausblenden(elementname) {
		document.getElementById('element').style.display = 'none';
	}
</script>
<a href="javascript:einblenden('element');">Entwicklerbuttons einblenden</a> <a href="javascript:ausblenden('element');"> oder ausblenden</a>

<div id="element" style="display: none">
	<form action="controller" method="post">
		<!-- Controller-Servlet wird aufgerufen und entscheidet, wohin geleitet wird -->
		<input type="hidden" name="logout" value="track"> <input
			type="submit" name="upload" value="Track hochladen"> <br>
		<input type="submit" name="show" value="Tracks anzeigen"> <br>
	</form>

	<form action="controller" method="post">
		<input type="hidden" name="action" value="album"> <input
			type="submit" name="upload" value="Album hochladen"> <br>
		<input type="submit" name="showalbum" value="Album anzeigen">
		<br>
	</form>

	<form action="controller" name="controlleraction" method="post">
		<input type="hidden" name="action" value="trackEditieren"> <input
			type="submit" value="Track editieren"> <br>

	</form>

	<form action="controller" method="post">
		<input type="hidden" name="action" value="category"> <input
			type="submit" value="Kategorien verwalten">
	</form>

	<form action="controller" method="post">
		<input type="hidden" name="action" value="keyword"> <input
			type="submit" value="Schlüsselworte verwalten">
	</form>
</div>
<c:import url="footer.jsp" />