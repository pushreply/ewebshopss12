<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track hinzufügen</title>
</head>
<body>
<h3>Abum</h3>
 <img src="images/bild.jpg" width=120 height=130 alt="bild">
 <div float:right>
  Titel:<br/>
  Artist:<br>
  Diskanzahl:<br/>
  Preis:<br/>
  Anzahl<br>
  Trackanzahl:<br/>
  Label:<br>
  
  <h3>Tracks Hinzufügen</h3>
 </div>
 <form>
 <table>
   <tr>
     <td align="right">Datei:</td>
     <td><input type="file" name='bearbeiten'></td>
   </tr>
 </table>

 <input type=button onClick="location.href='index.jsp'" value='Track hinzufügen'> 
</form>

</body>
</html>