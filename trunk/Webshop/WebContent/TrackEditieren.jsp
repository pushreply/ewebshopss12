<%@ include file="header.jsp"%>

<title>Track Editieren</title>
</head>
<body>

	<h3>
		Track editieren:
		<c:out value="${track.trackTitle}" default="N/A" />
	</h3>


	<form action="controller" method="get" enctype="multipart/form-data"
		onSubmit="return validateTrack(this,var_1)">
		<input type="hidden" name="updateTrack"
			value="<c:out value="${track.identifier}"/>" /> <input type="hidden"
			name="albumIdentifier" value="${albumIdentifier}" /> <input
			type="hidden" name="action" value="track" />
		<table>
			<tr>
				<td align="right"><div class="labeluser">Titel:</div></td>
				<td><input type="text" name="titel"
					value="<c:out value="${track.trackTitle}"  default="N/A" />" />
				</td>
			</tr>
			<tr>
				<td align="right"><div class="labeluser">Artist:</div></td>
				<td><input type="text" name="artist"
					value="<c:out value="${track.trackArtist}"  default="N/A" />" />
				</td>
			</tr>
			<tr>
				<td align="right"><div class="labeluser">Jahr:</div></td>
				<td><input type="text" name="date"
					value="<c:out value="${track.trackDate}"  default="N/A" />" />
				</td>
			</tr>
			<tr>
				<td align="right"><div class="labeluser">Genre:</div></td>
				<td><input type="text" name="genre"
					value="<c:out value="${track.trackGenre}"  default="N/A" />" />
				</td>
			</tr>
			<tr>
				<td align="right"><div class="labeluser">TrackNr.:</div></td>
				<td><input type="text" name="trackAnzahl"
					value="<c:out value="${track.trackNumber}"  default="N/A" />" />
				</td>
			</tr>
			<tr>
				<td align="right"><div class="labeluser">DiskNr.:</div></td>
				<td><input type="text" name="diskAnzahl"
					value="<c:out value="${track.trackDiskNumber}"  default="N/A" />" />
				</td>
			</tr>
			<tr>
		<td></td>
		<td><p>
		<input class="userbutton" type="submit" value="Speichern"
			onclick="return validateTrack(this.form,var_1,msg_1)">
		</tr>	</table>
	</form>


	<%@ include file="footer.jsp"%>