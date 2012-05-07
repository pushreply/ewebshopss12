<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="header.jsp" %>
<head>
<title>Album anlegen</title>
</head>
<script language="javascript" type="text/javascript">
   function boxcheck()
  	{
  	var auswahl = "";
  	
  	for(var i = 0; i < document.checkin.category.length; i++)
  	{
  		if(document.checkin.category[i].checked)
  		{
  		auswahl = auswahl + document.checkin.category[i].value + ";";
  		}
  	}
 	document.checkin.categoryies.value = auswahl;
  }
  //-->
  </script>
<body>
  <h3>Abum anlegen</h3>

<form action="controller" method="post" enctype="multipart/form-data">
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
      <td align="right">Price:</td>
      <td><input type="text" name="price" type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right">Label:</td>
      <td><input type="text" name="label" type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right">Trackanzahl:</td>
      <td><input type="text" name="trackanzahl" type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right">Diskanzahl:</td>
      <td><input type="text" name="diskanzahl" type="text" size="30" maxlength="40"></td>
    </tr>
	<tr>
      <td align="right">Album Anzahl</td>
      <td><input type="text" name="albumanzahl" type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
    	 <td align="right">Kategorie:</td>
        <c:forEach items="${categories}" var="category">
    	<td><input type="checkbox" id="{category.categoryName}" name="category" value="${category.identifier}"
    	${fn:contains(parts.category, '{category.identifier}') ? 'categoried' : ''}>
    	<c:out value = "${category.categoryName}"/></td>
    </c:forEach>
    </tr>
    <tr>
        <c:forEach items="${keywordies}" var="keyword">
    	<td><input type="checkbox" id="{keyword.keywordName}" name="keyword" value="${keyword.identifier}"
    	${fn:contains(parts.keyword, '{keyword.identifier}') ? 'keywordied' : ''}>
    	<c:out value = "${keyword.keywordName}"/></td>
    </c:forEach>
    </tr>
    <tr>
      <td align="right">Cover-Page:</td>
      <td><input type="file" name="coverpage" value ="Datei auswählen"type="text" size="30" maxlength="40"></td>
    </tr>
    <tr>
      <td align="right"></td>
      <td><input type="submit" name="senden" value="senden" type="text" size="30" maxlength="40" onClick="showHint()"></td>
    </tr>
  </table>
</form>
    

<%@ include file="footer.jsp" %>
