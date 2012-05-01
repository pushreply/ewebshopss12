<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Bitte loggen Sie sich ein" />
</c:import>

<form name="login" action="controller" method="post" accept-charset="ISO-8859-1">
	<ul>
		<li>Benutzername: <input name="username" type="text" />
    						<input name="action" type="hidden" value="login"/></li>
		<li>Kennwort: <input name="password" type="password" />
						<input name="action" type="hidden" value="login"/></li>
	</ul>
	<input type="submit" name="action" value="Login"/>
	<p>
	<a href="controller?action=register">oder Registrieren</a> 
                		
<c:import url="footer.jsp" />