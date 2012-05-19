<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- ending tag in footer.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page errorPage="/errorPage.jsp"%>

<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevent caching at the proxy server
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${param.title}</title>
<link rel="stylesheet" href="stylesheet/style.css" type="text/css"></link>
<script type="text/JavaScript" src="js/albumDatenAuswerten.js"></script>

</head>
<body>
	<!-- ending tag in footer.jsp -->
	<div id=wrapper>
		<div id="top">
			<!-- include all urls according to URL-Rewriting rules -->
			<%@ include file="urlVariables.jsp"%>
			<ul id="topmenue">
				<li><a href="${home}">Home</a></li>
				<li><a href="${alben}">Alben Ansehen</a></li>
				<c:choose>
					<c:when test="${isAdmin=='true'}">
						<li><a href="${keyword}">Schlüsselworte</a></li>
						<li><a href="${category}">Kategorien</a></li>
						<li><a href="${uploadAlbum}">Neues Album Erstellen</a></li>
					</c:when>
				</c:choose>
			</ul>
			<div id="login">
				<c:choose>
					<c:when test="${empty username}">
			Sie sind als Gast angemeldet. <a href="login.jsp">Hier anmelden.</a>
					</c:when>
					<c:when test="${not empty username}">
			Sie sind als <strong>${username}</strong> angemeldet.
						<a href="${logout}">Logout</a>
					</c:when>
				</c:choose>
			</div>

			<div id="search">
				<!-- just for displaying purposes, not linked with bean so far -->
				<input id="searchfield" type="text" /><input type="button" value="Suchen" />
				<ul><li><a href="${simplesearch}">Einfache Suche</a></li></ul>
			</div>
			<!-- search -->
		</div>
		<!-- top -->
		<br /> <br />
		<div id="main">