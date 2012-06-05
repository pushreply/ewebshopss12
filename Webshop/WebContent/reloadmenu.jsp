<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page errorPage="/errorPage.jsp"%>
<head>
<script type="text/javascript">
<!--
	function delayer() {
		window.location = "controller?action=home";
	}
</script>
<link rel="stylesheet" href="stylesheet/style.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sie werden weitergeleitet..</title>
</head>
<body onLoad="setTimeout('delayer()', 1000)">
	<c:choose>
		<c:when test="${empty username}">
			Sie sind nun ausgeloggt und </a>
		</c:when>
		<c:when test="${not empty username}">
			Willkommen <strong>${username}</strong>, Sie 
		</c:when>
	</c:choose>
	werden weitergeleitet..
</body>
</html>