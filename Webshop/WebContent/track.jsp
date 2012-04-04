<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% for(int i = 1; i < 4; i++ ) 

{

%>
<table width="589" border="1">
  <tr>
    <td width="196">${DBTrack[i].trackTitle}</td>
    <td width="141">Probe2</td>
    <td width="103">Probe3</td>
    <td width="121">Probe4</td>
  </tr>
</table>
<%
}
%>
</body>
</html>