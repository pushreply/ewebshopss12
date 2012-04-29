<!-- Autor: Benjamin -->

<%@ include file="header.jsp" %>

<title>Webshop Startseite</title>
</head>
<body>
<p><b>Webshop Startseite</b><p>


<p>Menü</p>

<br>
<br>
<!-- <input type="submit" name="albumHochladenButton" value="Album hochladen"> <br>	
	<input type="submit" name="suchenButton" value="Suchen"> <br>
 -->	

<form action="controller" method="post">	<!-- Controller-Servlet wird aufgerufen und entscheidet, wohin geleitet wird -->
	<input type="hidden" name ="action" value="track">
 	<input type="submit" name="upload" value="Track hochladen"> <br>
	<input type="submit" name="show" value="Tracks anzeigen"> <br>
</form>

<form action="controller" name="controlleraction" method="post">
	<input type="submit" name="trackEditieren" value="Track editieren"> <br>
	
</form>



<%@ include file="footer.jsp" %>