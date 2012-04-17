<!-- Autor: Benjamin -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Webshop Startseite</title>
</head>
<body>

<p><b>Webshop Startseite</b><p>

<br>
<br>
<br>
<br>

<p>Menü</p>

<br>
<br>

<form action="controller" name="controlleraction" method="post">	<!-- Controller-Servlet wird aufgerufen und entscheidet, wohin geleitet wird -->

	<input type="submit" name="albumHochladenButton" value="Album hochladen"> <br>	
	<input type="submit" name="suchenButton" value="Suchen"> <br>
	<input type="submit" name="trackHochladenButton" value="Track hochladen"> <br>
		
</form>


</body>
</html>