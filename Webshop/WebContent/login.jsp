<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Bitte loggen Sie sich ein" />
</c:import>

<form name="login" action="controller?action=Login" method="post" accept-charset="ISO-8859-1">
<ul>
               <li>Benutzername: <input name="username" type="text" value=""/></li>
                <li>Kennwort: <input name="password" type="password" value=""/></li>
</ul>
                		<input type="submit" name="action" value="login"/>
                		<p>
                		<a href="controller?register">oder Registrieren</a> 
                		
<c:import url="footer.jsp" />