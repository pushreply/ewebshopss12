<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Es tut uns leid!" />
</c:import>
<p>
<h3><c:out value="${pageContext.exception.message}" default="Registration Error" /></h3>
<p>

<form action="controller" method="post">
<ul>
<li><div class="label">Wählen Sie einen anderen Benutzernamen:</div> <input name="username" type="text"/></li>
<li><div class="label">und ein Kennwort:</div> <input name="pw1" type="password" value=""/></li>
<li><div class="label">Bitte bestätigen Sie das Kennwort erneut:</div><input name="pw2" type="password"/></li>
</ul>

<input type="submit" name="action" value="Registrieren" > 
<input name="action" type="hidden" value="register"/>
	<input type="reset" value="Reset">
</form>


<c:import url="footer.jsp" />

