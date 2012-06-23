<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Registrierung" />
</c:import>

<p>
<form action="controller" method="post">
<h2>Neue Registrierung </h2>
<p>

<ul>
<li><div class="label">Wählen Sie einen Benutzernamen:</div> <input name="username" type="text"/></li>
<li><div class="label">und ein Kennwort:</div> <input name="pw1" type="password" value=""/></li>
<li><div class="label">Bitte bestätigen Sie das Kennwort erneut:</div><input name="pw2" type="password"/></li>
</ul>
<p><h3>Wir benötigen folgende Angaben von Ihnen:</h3></p>

<ul>
<li><div class="label">
Anrede: 
</div>
<li><input type="radio" name="gender" value="male" /> Herr &nbsp;
<input type="radio" name="gender" value="female" checked="checked" />Frau</li>

<li><div class="label">Vorname:</div><input name="fname" type="text"/></li>
<li><div class="label">Nachname:</div><input name="lname" type="text"/></li>
<li><div class="label">Strasse und Hausnummer:</div><input name="street" type="text"/> &nbsp; (Beispiel: Amerikastr. 1)</li>
<li><div class="label">PLZ und Stadt:</div><input name="country" type="text"/> &nbsp; (Beispiel: 66482 Zweibrücken) </li>

</ul>
<input class="userbutton" type="submit" value="Registrieren" > 
<input type="hidden" name="action"value="register"/>
	<input class="userbutton" type="reset" value="Reset">
</form>


<c:import url="footer.jsp" />