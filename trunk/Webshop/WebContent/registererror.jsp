<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Es tut uns leid!" />
</c:import>
<p>
<h3>Leider ist die ausgewählte Benutzername: <strong><c:out value="${customer.username}" /></strong> von anderen Kunden belegt.</h3>
<p>

<form action="controller" method="post">
<ul>
Bitte wählen Sie einen anderen Benutzernamen: 
<li><input name="username" type="text"/>
<input name="action" type="hidden" value="register"/>
</li>
und einen Kenntwort: 
<li><input name="password1" type="password" value=""/>
<input name="action" type="hidden" value="register" />
</li>
Bitte den Kenntwort noch mal bestätigen:
<li><input name="password2" type="password"/></li>
</ul>

<input type="submit" name="action" value="Registrieren" > 
	<input type="reset" value="Reset">
</form>


<c:import url="footer.jsp" />

