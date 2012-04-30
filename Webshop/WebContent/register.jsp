<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Registrierung" />
</c:import>

<p>
<form action="controller" method="post">
Registrieren: <p>
<ul class="">
Wählen Sie eine Benutzername:
<li><input id="username" name="username" type="text"/></li>
und einen Kenntwort: 
<li><input id="password" name="password1" type="password"/></li>
Bitte den Kenntwort noch mal bestätigen:
<li><input id="password" name="password2" type="password"/></li>
</ul>
<input type="submit" name="action" value="Registrieren" > <input type="reset" value="Reset">
</form>


<c:import url="footer.jsp" />