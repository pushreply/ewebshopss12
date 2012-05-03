<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isErrorPage="true" %>

<c:import url="header.jsp">
	<c:param name="title" value="Webshop Startseite" />
</c:import>


<h1 id="errorheading">EIN FEHLER IST AUFGETRETEN</h1>
<p><c:out value="${pageContext.exception.message}" default="Es ist ein gänzlich unbekannter Fehler aufgetreten. Diese Fehlermeldung sollte nie auftauchen. Wenn Sie sie trotzdem sehen, wenden sie sich bitte an uns." />

<input type="button" value="Zurück" onClick="history.back()">

<c:import url="footer.jsp" /> 