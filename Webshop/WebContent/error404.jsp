<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true" %>
<c:import url="header.jsp">
	<c:param name="title" value="Fehler 404" />
</c:import>

<h1 id="errorheading">FEHLER 404</h1>
<p>Die von ihnen aufgerufene Seite kann nicht gefunden werden :'-(. <br />
Bitte wählen Sie eine eine Andere aus, sie wird sich sicherlich freuen.</p>

<c:import url="footer.jsp" />
