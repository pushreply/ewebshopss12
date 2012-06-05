<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isErrorPage="true" %>

<c:import url="header.jsp">
	<c:param name="title" value="Webshop Startseite" />
</c:import>


<h1 >Fehler bei der Bestellung</h1>
<p><c:out value="${meldung}"/>

<input type="button" value="Zurück" onClick="history.back()">

<c:import url="footer.jsp" /> 