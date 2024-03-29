<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="header.jsp">
	<c:param name="title" value="Einfache Suche" />
</c:import>
<head>
<title>Suche</title>
</head>
<body>
  <h3>Suche</h3>
  <form action="controller" method="post">
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
    	 <td  class="table-label">Kategorie: </td>
        <td><c:forEach items="${categories}" var="category">
    	<li><input type="checkbox" id="{category.categoryName}" name="category" value="${category.categoryName}">
    	<c:out value = "${category.categoryName}"/></li>
    </c:forEach>
    </tr>
    <tr>
				<td class="table-label">Schlagw&ouml;rter:</td>
				<td><ul class="bulletless-list"><c:forEach items="${keywordies}" var="keyword">
    	<li><input type="checkbox" id="{keyword.keywordName}" name="keyword" value="${keyword.keywordName}">
    	<c:out value = "${keyword.keywordName}"/></li>
    </c:forEach></ul></td>
    </tr>
		<tr>
	</table>
		<inputtype="hidden" name="action" value="albumsearch"> <input
			type="hidden" name="search" value="true"> <input  class="userbutton" type="submit"
			value="Suchen">
	</form>
</body>
<c:import url="footer.jsp" />
</html>