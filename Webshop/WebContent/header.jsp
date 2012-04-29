<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${param.title}</title>
<link rel="stylesheet" href="stylesheet/style.css" type="text/css"></link>
</head>
<body>
	Du bist als
	<strong><%=session.getAttribute("username")%> </strong> angemeldet.
	<form name="logout" action="controller" method="get">
		<button name="action" type="submit" value="Logout" class="buttonslog">Logout</button>
	</form>

	<!-- include all urls according to URL-Rewriting rules -->
	<%@ include file="urlVariables.jsp"%>
	<ul class="topmenue">
		<li><a href="${home}">Home</a></li>
		<li><a href="${trackUpload}">Lied Hochladen</a></li>
		<li><a href="${trackShow}">Alle Lieder Anzeigen</a></li>
		<li><a href="${keyword}">Schlüsselworte</a></li>
		<li><a href="${category}">Kategorien</a></li>
	</ul>
	<br />
	<br />
	//ende mit header
	<hr />