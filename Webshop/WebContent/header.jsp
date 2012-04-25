<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body>
Du bist als <strong><%=session.getAttribute("username")%> </strong> angemeldet.
	<form name="logout" action="controller" method="get">
		<button name="action" type="submit" value="Logout" class="buttonslog">Logout</button>
	</form>

 


<table border="1">
<tr>
<td><a href="index.jsp">Home</td>
<td><a href="controller?trackHochladenButton">Upload</td>
</tr>
<br>


</table>
----header.jsp---