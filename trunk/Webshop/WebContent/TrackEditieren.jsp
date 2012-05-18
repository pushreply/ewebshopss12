<%@ include file="header.jsp"%>

<title>Track Editieren</title>
</head>
<body>

	<h3>Track editieren: <c:out value="${track.trackTitle}"  default="N/A" /></h3>


	<form action="controller" method="get" enctype="multipart/form-data">
	<input type="hidden" name="updateTrack" value="<c:out value="${track.identifier}"/>"/>
	<input type="hidden" name="albumIdentifier" value="${albumIdentifier}"/>
	<input type="hidden" name="action" value="track"/>
  <table>
    <tr>
      <td align="right">Titel:</td>
      <td><input type="text" name="titel" value="<c:out value="${track.trackTitle}"  default="N/A" />"/></td>
    </tr>
    <tr>
      <td align="right">Artist:</td>
      <td><input type="text" name="artist"  value="<c:out value="${track.trackArtist}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Jahr:</td>
      <td><input type="text" name="date" value="<c:out value="${track.trackDate}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Genre:</td>
      <td><input type="text" name="genre" value="<c:out value="${track.trackGenre}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">TrackNr.:</td>
      <td><input type="text" name="trackAnzahl" value="<c:out value="${track.trackNumber}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">DiskNr.:</td>
      <td><input type="text" name="diskAnzahl" value="<c:out value="${track.trackDiskNumber}"  default="N/A" />" /></td>
    </tr>
  </table>
  <input type="submit" value="Speichern">
</form>


	<%@ include file="footer.jsp"%>