<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Hallo ${userprofile.username}" />
</c:import>

<h3>Hallo
<c:choose>
	<c:when test="${userprofile.addresses[0].gender=='male'}">Herr</c:when>
	<c:when test="${userprofile.addresses[0].gender=='female'}">Frau</c:when>
</c:choose>
<c:out value="${userprofile.username}" />,</h3>


<!-- <c:out value="${userprofile.addresses[0].firstName}" /> -->
<!-- <c:out value="${userprofile.addresses[0].lastName}" /> -->

<p>Ihre Angaben:
<ul>
	<c:forEach items="${userprofile.addresses}" var="userprofileitem">
		<strong><c:choose>
			<c:when test="${userprofileitem.art == delivery}">
			Lieferadresse:
			</c:when>
			<c:when test="${userprofileitem.art == billing}">
			Rechnungsadresse:
			</c:when>
		</c:choose></strong>
		<li><c:out value="${userprofileitem.firstName}"/> <c:out value="${userprofileitem.lastName}"/></li>
		<li><c:out value="${userprofileitem.street}"/></li>
		<li><c:out value="${userprofileitem.country}" /></li>
		<li><form action="controller" method="post">
				<input type="hidden" name="action" value="customer"> 
				<input type="hidden" name="address" value="delete"> 
				<input type="submit" value="Adress l&ouml;schen"></form>
		</li>	
	</c:forEach>
</ul>

<p>
<h3>Neue Adresse hinzufügen </h3>
<form action="controller" method="post">
<ul>
	<li>Strasse - HausNr.:</li>
	<li><input name="street" type="text"/>
		<input name="action" type="hidden" value="addnew"/></li>
	<li>PLZ - Land:</li>
	<li><input name="country" type="text"/>
		<input name="action" type="hidden" value="addnew"/></li>
	<li><input type="radio" name="art" value="delivery" /> Lieferadresse &nbsp;
		<input type="radio" name="art" value="billing" />Rechnungsadresse
		<input name="action" type="hidden" value="addnew"/></li>
</ul>
	<input type="submit" value="Adress Speichern">
	<input type="hidden" name="action" value="address"> 
	<input type="hidden" name="address" value="addnew"> 
</form>
				
<c:import url="footer.jsp" />