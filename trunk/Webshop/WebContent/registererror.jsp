<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Es tut uns leid!" />
</c:import>
<p>
<h3>Leider ist die ausgewählte Benutzername: <strong><c:out value="${customer.username}" /></strong> von anderen Kunden belegt.</h3>
<p>

<form action="controller" method="post">
<ul class="">
Bitte wählen Sie einen anderen Benutzernamen: 
<li><input id="username" name="username" type="text"/></li>
und einen Kenntwort: 
<li><input id="password" name="password1" type="password"/></li>
Bitte den Kenntwort noch mal bestätigen:
<li><input id="password" name="password2" type="password"/></li>
</ul>
<input type="submit" name="action" value="registrieren"> <input type="reset"  value="Reset">
</form>


<c:import url="footer.jsp" />

