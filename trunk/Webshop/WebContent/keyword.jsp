<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schlüsselworte</title>
</head>
<body>
<h2>Neues Schlüsselwort hinzufügen</h2>
	<form action="controller" method="post">
		<input type="text" name="addKeyword" value="">
		<input type="hidden" name = "action" value="keyword">
		<input type="submit" value="Hinzufügen">
	</form>
	
<h2>Alle Schlüsselworte</h2>
	<table width="589" border="1">
		<c:forEach items="${keywords}" var="keyword">
			<tr>
				<td>${keyword.keywordName}</td>
				<td><form action="controller" method="get">
						<input type="hidden" name="deleteKeyword" value="${keyword.identifier}">
						<input type="hidden" name = "action" value="keyword">
						<input type="submit" value="Delete">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>