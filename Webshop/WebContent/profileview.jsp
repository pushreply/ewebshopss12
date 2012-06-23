<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Hallo ${username}" />
</c:import>

<h3>Hallo
	<c:if test="${userprofile.addresses[0].gender=='male'}">Herr</c:if>
	<c:if test="${userprofile.addresses[0].gender=='female'}">Frau</c:if>
<c:out value="${username}" />,</h3>

<p>Ihre Angaben:
<form action="controller" method="post">
<ul>
	<c:forEach items="${userprofile.addresses}" var="userprofileitem">
		<li><strong>
			<c:if test="${userprofileitem.art == 'delivery'}">Lieferadresse:</c:if>
			<c:if test="${userprofileitem.art == 'billing'}">Rechnungsadresse:</c:if>
			</strong></li>
		<li><c:out value="${userprofileitem.firstName}"/> <c:out value="${userprofileitem.lastName}"/></li>
		<li><c:out value="${userprofileitem.street}"/></li>
		<li><c:out value="${userprofileitem.country}" /></li>
		<li>
		<a href="controller?action=address&delete=<c:out value="${userprofileitem.identifier}"/> ">Adresse l&ouml;schen</a>
		</li>	
		
	</c:forEach>
</ul>
</form>


<p>
<h3>Neue Adresse hinzufügen </h3>
<form action="controller" method="post">

<ul>
	<li><input type="radio" name="art" value="delivery" checked="checked"/>Lieferadresse &nbsp;
		<input type="radio" name="art" value="billing" />Rechnungsadresse
	</li>
	<li><div class="labeluser">Vorname:</div><input name="firstName" type="text"/></li>
	<li><div class="labeluser">Nachname:</div><input name="lastName" type="text"/></li>
	<li><div class="labeluser">Strasse - HausNr.:</div><input name="street" type="text"/></li>
	<li><div class="labeluser">PLZ - Stadt:</div><input name="country" type="text"/></li>
</ul> 
	<input class="userbutton" type="submit" value="Adresse Speichern">
	<input type="hidden" name="action" value="address"> 
	<input name="address" type="hidden" value="addnew"/>
	
</form>
				
<c:import url="footer.jsp" />