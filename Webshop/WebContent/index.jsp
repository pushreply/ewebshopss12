<!-- Autor: Benjamin -->

<%@ include file="header.jsp" %>

<title>Webshop Startseite</title>
</head>
<body>
<p><b>Webshop Startseite</b><p>


<p>Men�</p>

<br>
<br>

<form action="controller" name="controlleraction" method="post">	<!-- Controller-Servlet wird aufgerufen und entscheidet, wohin geleitet wird -->

	<input type="submit" name="albumHochladenButton" value="Album hochladen"> <br>	
	<input type="submit" name="suchenButton" value="Suchen"> <br>
	<input type="submit" name="trackHochladenButton" value="Track hochladen"> <br>
</form>



<%@ include file="footer.jsp" %>