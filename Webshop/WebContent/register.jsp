<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Registrierung" />
</c:import>

<p>
<form action="controller" method="post">
Registrieren: <p>
<ul>
W�hlen Sie eine Benutzername:
<li><input name="username" type="text"/>
<input name="action" type="hidden" value="register"/>
</li>
und einen Kenntwort: 
<li><input name="password1" type="password" value=""/>
<input name="action" type="hidden" value="register" />
</li>
Bitte den Kenntwort noch mal best�tigen:
<li><input name="password2" type="password"/></li>
</ul>

<input type="submit" name="action" value="Registrieren" > 
	<input type="reset" value="Reset">
</form>


<c:import url="footer.jsp" />