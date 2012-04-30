<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Bitte loggen Sie sich ein" />
</c:import>

<form name="login" action="controller?action=Login" method="post" accept-charset="ISO-8859-1">
                <input name="username" type="text" value="Username"/>
                <input name="password" type="text" value="Passwort"/>

                		<input type="submit" name="action" value="Login"/>
                		<p>
                		<a href="controller?register">oder Registrieren</a> 
                		
<c:import url="footer.jsp" />