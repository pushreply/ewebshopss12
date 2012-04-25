<%@ include file="header.jsp"%>

<title>Track Editieren</title>
</head>
<body>

	<h3>Track editieren: <c:out value="${track.trackTitle}"  default="N/A" /></h3>


	<form action="Album.jsp ">
  <table border="0" cellpadding="0" cellspacing="4">
    <tr>
      <td align="right">Titel:</td>
      <td><input type="text" name="titel" size="30" maxlength="30" value="<c:out value="${track.trackTitle}"  default="N/A" />"/></td>
    </tr>
    <tr>
      <td align="right">Artist:</td>
      <td><input type="text" name="artist" size="30" maxlength="40" value="<c:out value="${track.trackArtist}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Jahr:</td>
      <td><input type="text" name="date" size="30" maxlength="40" value="<c:out value="${track.trackDate}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Genre:</td>
      <td><input type="text" name="genre" size="30" maxlength="40" value="<c:out value="${track.trackGenre}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Trackanzahl:</td>
      <td><input type="text" name="trackanzahl" size="30" maxlength="40" value="<c:out value="${track.trackNumber}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Diskanzahl:</td>
      <td><input type="text" name="diskanzahl" size="30" maxlength="40" value="<c:out value="${track.trackDiskNumber}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right"></td>
      <td><input type="submit" name="senden" value="senden" size="30" maxlength="40"></td>
    </tr>
  </table>
</form>


	<%@ include file="footer.jsp"%>