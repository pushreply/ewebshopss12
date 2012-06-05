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
			<jsp:useBean id="orderedAlben" scope="session"
				class="java.util.LinkedList" />
			<ul id="topmenue">
				<li><a href="${home}">Startseite</a></li>
				<li><a href="${alben}">Alben durchstöbern</a></li>
				<li><a href="${order}">Warenkorb (<strong><c:out
								value='${orderedAlben.size()}' /></strong> Artikel)
				</a></li>
				<c:choose>
					<c:when test="${isAdmin=='true'}">
						<li><a href="${keyword}">Schlüsselworte</a></li>
						<li><a href="${category}">Kategorien</a></li>
						<li><a href="${uploadAlbum}">Neues Album anlegen</a></li>
					</c:when>
				</c:choose>
			</ul>
			<div id="login">
				<c:choose>
					<c:when test="${empty username}">
			Sie sind als Gast angemeldet. <a href="login.jsp">Hier anmelden.</a>
					</c:when>
					<c:when test="${not empty username}">
			 Sie sind als <a href="controller?action=customer&show=profile"><strong>${username}</strong></a> angemeldet.
			 <br />
						<a href="${logout}">Sind Sie nicht ${username}?</a>
					</c:when>
				</c:choose>
			</div>
			<div id="search">
				<!-- just for displaying purposes, not linked with bean so far -->
				<!-- <input id="searchfield" type="text" /><input type="button"
					value="Suchen" />
				<!--<ul>-->
				<!--<li><a href="${simplesearch}">Einfache Suche</a></li>-->
				<a href="${simplesearch}">Zur Suche</a>
				<!--</ul>-->
			</div>
			<!-- search -->
		</div>
		<!-- top -->
		<br /> <br />
		<div id="main">