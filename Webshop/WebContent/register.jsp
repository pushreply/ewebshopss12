<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Registrierung" />
</c:import>

<p>
<form action="controller" method="post">
<strong>Neue Registrierung </strong>
<p>
<ul>
Wählen Sie eine Benutzername:
<li><input name="username" type="text"/>
<input name="action" type="hidden" value="register"/>
</li>
und einen Kenntwort: 
<li><input name="pw1" type="password" value=""/>
<input name="action" type="hidden" value="register" />
</li>
Bitte den Kenntwort noch mal bestätigen:
<li><input name="pw2" type="password"/></li>
<p>
<strong>Angabe zu Ihre Persönliche Daten.</strong>
<p>
Anrede: 

<li><input type="radio" name="gender" value="male" /> Herr &nbsp;
<input type="radio" name="gender" value="female" />Frau
<input name="action" type="hidden" value="register"/></li>
Vorname:
<li><input name="fname" type="text"/>
<input name="action" type="hidden" value="register"/></li>
Nachname:
<li><input name="lname" type="text"/>
<input name="action" type="hidden" value="register"/></li>
Strasse und HausNr.:
<li><input name="street" type="text"/>
<input name="action" type="hidden" value="register"/></li>
PLZ und Land:
<li><input name="country" type="text"/>
<input name="action" type="hidden" value="register"/></li>

</ul>

<input type="submit" name="action" value="Registrieren" > 
	<input type="reset" value="Reset">
</form>


<c:import url="footer.jsp" />