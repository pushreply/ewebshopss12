<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h3>Abum anlegen</h3>

<form action=" ">
  <table border="0" cellpadding="0" cellspacing="4">
    <tr>
      <td align="right">Titel:</td>
      <td><input type="text" name="titel" type="text" size="30" maxlength="30"></td>
    </tr>
    <tr>
      <td align="right">Artist:</td>
      <td><input type="text" name="artist" type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right">Date:</td>
      <td><input type="text" name="date" type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right">Genre:</td>
      <td><input type="text" name="genre" type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right">Trackanzahl:</td>
      <td><input type="text" name="trackanzahl" type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right">Diskanzahl:</td>
      <td><input type="text" "diskanzahl" type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right">Cover-Page:</td>
      <td><input type="file" name="coverpage" value ="Datei auswählen"type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right"></td>
      <td><input type="submit" name="senden" value="senden" type="text" size="30" maxlength="40"></td>
    </tr>
  </table>
</form>
</body>
</html>