<%@ include file="header.jsp" %>
<title>Track hinzuf�gen</title>
</head>
<body>

<h3>Album</h3>


 <form action="controller"  		
 		method="post"
 		enctype="multipart/form-data">
 		<table border=1>
  <tr><td>Titel:</td><td><c:out value="" /></td></tr>
  <tr><td>Artist:</td><td><c:out value="" /></td></tr>
  <tr><td>Year:</td><td><c:out value="" /></td></tr>
  <tr><td>Genre</td><td><c:out value="" /></td></tr>
  <tr><td>Track Number:</td><td><c:out value="" /></td></tr>
 <!--  <tr><td>Album Cover:</td><td><c:out value="" /></td></tr>-->
 
  <h3>Tracks Hinzuf�gen</h3>

 	
 	</table>
   		<tr>
     		<td align="right">Datei:</td>
    		<td><input type="file" name="file" id="file"></td>
   		</tr>
	 </table>

	 <input type=submit name="uploadFileSubmitButton" value='Upload File'> 
	</form>
</table>

<%@ include file="footer.jsp" %>