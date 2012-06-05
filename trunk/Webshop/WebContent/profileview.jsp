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
<form action="controller" method="post">
<ul>
	<c:forEach items="${userprofile.addresses}" var="userprofileitem">
		<li><strong>
		
			<c:if test="${userprofileitem.art == 'delivery'}">
			Lieferadresse:
			</c:if>
			<c:if test="${userprofileitem.art == 'billing'}">
			Rechnungsadresse:
			</c:if>
		
</strong></li>
		<li><c:out value="${userprofileitem.firstName}"/> <c:out value="${userprofileitem.lastName}"/></li>
		<li><c:out value="${userprofileitem.street}"/></li>
		<li><c:out value="${userprofileitem.country}" /></li>
		<li>
		<a href="controller?action=address&delete=<c:out value="${userprofileitem.identifier}"/> ">Adresse l&ouml;schen</a>
<!-- 				<input type="hidden" name="action" value="address"> -->
<%-- 				<input type="hidden" name="delete" value="${userprofileitem.identifier}">  --%>
<!-- 				<input type="submit" value="Adresse l&ouml;schen"> -->
		</li>	
	</c:forEach>
</ul>
</form>

<p>
<h3>Neue Adresse hinzufügen </h3>
<form action="controller" method="post">
<ul>
	<li>Strasse - HausNr.:</li>
	<li><input name="street" type="text"/>
		</li>
	<li>PLZ - Land:</li>
	<li><input name="country" type="text"/></li>
	<li><input type="radio" name="art" value="delivery" checked="checked"/> Lieferadresse &nbsp;
		<input type="radio" name="art" value="billing" />Rechnungsadresse
	</li>
</ul>
	<input type="submit" value="Adresse Speichern">
	<input type="hidden" name="action" value="address"> 
	<input name="address" type="hidden" value="addnew"/>
</form>
				
<c:import url="footer.jsp" />