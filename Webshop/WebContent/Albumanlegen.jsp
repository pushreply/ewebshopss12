<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Album anlegen" />
</c:import>
<head>
<title>Album anlegen</title>
</head>
<body>
	<h3>Album anlegen</h3>

	<form name="Formular" action="controller" method="post"
		enctype="multipart/form-data" onSubmit="return validate(this,var_1)">
		<table>
			<tr>
				<td class="table-label">Titel: </td>
				<td><input type="text" name="titel" type="text">
				</td>
			</tr>
			<tr>
				<td class="table-label">Artist: </td>
				<td><input type="text" name="artist" type="text">
				</td>
			</tr>
			<tr>
				<td class="table-label">Preis: </td>
				<td><input type="text" name="price" type="text">
				</td>
			</tr>
			<tr>
				<td class="table-label">Label:</td>
				<td><input type="text" name="label" type="text">
				</td>
			</tr>
			<tr>
				<td class="table-label">Anzahl der Tracks: </td>
				<td><input type="text" name="trackanzahl" type="text">
				</td>
			</tr>
			<tr>
				<td class="table-label">Anzahl der Disks: </td>
				<td><input type="text" name="diskanzahl" type="text">
				</td>
			</tr>
			<tr>
				<td class="table-label">Lagerbestand: </td>
				<td><input type="text" name="albumanzahl" type="text">
				</td>
			</tr>
			<tr>
			<td>&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td>
				<fieldset>
			<legend>Kategorie:</legend>
			<c:forEach items="${categories}" var="category">
						<li><input type="checkbox" id="{category.categoryName}"
							name="category" value="${category.identifier}"${fn:contains(parts.category, '{category.identifier}') ? 'categoried' : ''}>
							<c:out value="${category.categoryName}" />
						</li>
					</c:forEach></fieldset>
			
				<td></td>
				<td>
				<fieldset>
					<legend>Schlagw&ouml;rter:</legend>
						<c:forEach items="${keywordies}" var="keyword">
							<li><input type="checkbox" id="{keyword.keywordName}"
								name="keyword" value="${keyword.identifier}"${fn:contains(parts.keyword, '{keyword.identifier}') ? 'keywordied' : ''}>
								<c:out value="${keyword.keywordName}" />
							</li>
						</c:forEach>
				</fieldset>
				</td>
				
			</tr>
			<tr>
				<td class="table-label">Cover:</td>
				<td><input class="userbutton" type="file" name="coverpage" value="Datei auswählen"
					type="text">
				</td>
			</tr>
			<tr>
				<td class="table-label"></td>
				<td><input class="userbutton" type="submit" name="senden" value="Senden"
					onClick="showHint()"
					onclick="return validate(this.form,var_1,msg_1)">
				</td>
			</tr>
		</table>
	</form>


	<c:import url="footer.jsp" />