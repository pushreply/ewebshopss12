<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Bitte loggen Sie sich ein" />
</c:import>

<form name="login" action="controller" method="post"
	accept-charset="ISO-8859-1">
	<ul>
		<li><div class="label">Benutzername:</div> <input name="username"
			type="text" /> <input name="action" type="hidden" value="login" />
			<!-- <input name="username" type="hidden" value="identifier" /> --></li>
		<li><div class="label">Kennwort:</div> <input name="password"
			type="password" /> <input name="action" type="hidden" value="login" /></li>
	</ul>
	<input type="submit" name="action" value="Login" />
	<p>
		<a href="register.jsp">oder Registrieren</a>

		<c:import url="footer.jsp" />