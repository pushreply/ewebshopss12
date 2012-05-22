<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Hallo ${userprofile.username}" />
</c:import>

Hallo
<c:choose>
	<c:when test="${userprofile.addresses[0].gender=='male'}">Herr</c:when>
	<c:when test="${userprofile.addresses[0].gender=='female'}">Frau</c:when>
</c:choose>
<c:out value="${userprofile.username}" />
,

<!-- <c:out value="${userprofile.addresses[0].firstName}" /> -->
<!-- <c:out value="${userprofile.addresses[0].lastName}" /> -->

<p>Ihre Angaben:
<ul>
	<c:forEach items="${userprofile.addresses}" var="userprofileitem">
		<li>Vorname: <c:out value="${userprofileitem.firstName}" /></li>
		<li>Nachname: <c:out value="${userprofileitem.lastName}" /></li>
		<li>Strasse: <c:out value="${userprofileitem.street}" /></li>
		<li>PLZ und Land: <c:out value="${userprofileitem.country}" /></li>
	</c:forEach>
</ul>


<c:import url="footer.jsp" />