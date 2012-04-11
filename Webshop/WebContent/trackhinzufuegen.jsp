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
 <form action="uploadmusicfile"  		
 		method="post"
 		enctype="multipart/form-data">
 <table>
   <tr>
     <td align="right">Datei:</td>
     <td><input type="file" name="filename" id="fileid"></td>
   </tr>
 </table>

 <input type=submit 
 		onClick="location.href='index.jsp'" 
 		value='Upload File'

 		> 
</form>

</body>
</html>