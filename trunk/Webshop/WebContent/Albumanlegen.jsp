<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Album anlegen" />
</c:import>
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
  <h3>Album anlegen</h3>

<form action="controller" method="post" enctype="multipart/form-data">
  <table>
    <tr>
      <td class="table-label">Titel:</td>
      <td><input type="text" name="titel" type="text"></td>
    </tr>
    <tr>
      <td  class="table-label">Artist:</td>
      <td><input type="text" name="artist" type="text"></td>
    </tr>
    <tr>
      <td class="table-label">Price:</td>
      <td><input type="text" name="price" type="text"></td>
    </tr>
    <tr>
      <td  class="table-label">Label:</td>
      <td><input type="text" name="label" type="text"></td>
    </tr>
    <tr>
      <td  class="table-label">Trackanzahl:</td>
      <td><input type="text" name="trackanzahl" type="text"></td>
    </tr>
    <tr>
      <td  class="table-label">Diskanzahl:</td>
      <td><input type="text" name="diskanzahl" type="text"></td>
    </tr>
	<tr>
      <td  class="table-label">Album Anzahl</td>
      <td><input type="text" name="albumanzahl" type="text"></td>
    </tr>
    <tr>
    	 <td  class="table-label">Kategorie: </td>
        <td><c:forEach items="${categories}" var="category">
    	<li><input type="checkbox" id="{category.categoryName}" name="category" value="${category.identifier}"
    	${fn:contains(parts.category, '{category.identifier}') ? 'categoried' : ''}>
    	<c:out value = "${category.categoryName}"/></li>
    </c:forEach>
    </tr>
    <tr>
				<td class="table-label">Schlagw&ouml;rter:</td>
				<td><ul class="bulletless-list"><c:forEach items="${keywordies}" var="keyword">
    	<li><input type="checkbox" id="{keyword.keywordName}" name="keyword" value="${keyword.identifier}"
    	${fn:contains(parts.keyword, '{keyword.identifier}') ? 'keywordied' : ''}>
    	<c:out value = "${keyword.keywordName}"/></li>
    </c:forEach></ul></td>
    </tr>
    <tr>
      <td  class="table-label">Cover:</td>
      <td><input type="file" name="coverpage" value ="Datei ausw�hlen"type="text"></td>
    </tr>
    <tr>
      <td  class="table-label"></td>
      <td><input type="submit" name="senden" value="senden" onClick="showHint()"></td>
    </tr>
  </table>
</form>
    

<c:import url="footer.jsp" />