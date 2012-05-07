<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <!-- ending tag in footer.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page errorPage="/errorPage.jsp"%>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${param.title}</title>
<link rel="stylesheet" href="stylesheet/style.css" type="text/css"></link>

</head>
<body> <!-- ending tag in footer.jsp -->

	<c:choose>
		<c:when test="${empty username}">
Sie sind als Gast angemeldet. <a href="login.jsp">Hier anmelden.</a>
		</c:when>
		<c:when test="${!empty username}">
	Du bist als
	<strong>${username}</strong> angemeldet.
	<form name="logout" action="controller" method="get">
				<button name="action" type="submit" value="Logout"
					class="buttonslog">Logout</button>
			</form>
		</c:when>
	</c:choose>

	<!-- include all urls according to URL-Rewriting rules -->
	<%@ include file="urlVariables.jsp"%>
	<ul class="topmenue" align="center">
		<li><a href="${home}">Home</a></li>
		
		<li><a href="${alben}">Alle Alben</a></li>
		<li><a href="${trackShow}">Alle Tracks</a></li>
		<li><a href="${trackUpload}">Track hochladen</a></li>
		<li><a href="${keyword}">Schlüsselworte</a></li>
		<li><a href="${category}">Kategorien</a></li>
		
	</ul>
	<br />
	<br /> 
	<hr />