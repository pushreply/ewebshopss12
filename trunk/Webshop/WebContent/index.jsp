<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Webshop Startseite" />
</c:import>
<h1>Herzlich Willkommen</h1>
<p>
	auf musik.to !<br /> Auf dieser Seite können Sie sich durch eine
	willkürlich ausgewählte und außerdem sehr geringe Auswahl von mp3s
	wühlen und zu ihrem Warenkorb hinzufügen und herunterladen. Wenn Sie
	bereits wissen was Sie möchten, benutzen Sie am besten gleich die
	Suche.
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
<a href="javascript:einblenden('element');">Entwicklerbuttons
	einblenden</a>
<a href="javascript:ausblenden('element');"> oder ausblenden</a>

<div id="element" style="display:none">
	<%@ include file="urlVariables.jsp"%>
	<ul>
		<li><a href="${trackShow}">Alle Tracks anzeigen</a></li>
		<li><a href="${trackUpload}">Track hochladen</a></li>
		<li><a href="${keyword}">Schlüsselworte</a></li>
		<li><a href="${category}">Kategorien</a></li>
	</ul>
</div>
<c:import url="footer.jsp" />