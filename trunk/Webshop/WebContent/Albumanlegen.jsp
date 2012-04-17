<%@ include file="header.jsp" %>
<head>
<title>Album anlegen</title>
</head>
<body>
  <h3>Abum anlegen</h3>

<form action="Album.jsp ">
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

<%@ include file="footer.jsp" %>
