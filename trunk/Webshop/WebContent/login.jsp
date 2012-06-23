<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Bitte loggen Sie sich ein" />
</c:import>

<form name="login" action="controller" method="post"
	accept-charset="ISO-8859-1">
	
		<ul>
			<li>
				<div class="labeluser">Benutzername:</div> <input name="username" type="text" /> 
				<input name="action" type="hidden" value="login" />
			</li>
			<li>
				<div class="labeluser">Kennwort:</div> <input name="password" type="password" /> 
				<input name="action" type="hidden" value="login" />
			</li>
		<li>
			<input class="userbutton" type="submit" name="action" value="Login" />
		</li>
		</ul>
</form>
<a href="register.jsp">Jetzt kostenlos Registrieren</a>

<c:import url="footer.jsp" />