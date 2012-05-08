<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Kategorien" />
</c:import>

<h2>Neue Kategorie hinzuf�gen</h2>
<form action="controller" method="post">
	<input type="text" name="addCategory" value=""> <input
		type="hidden" name="action" value="category"> <input
		type="submit" value="Hinzuf�gen">
</form>

<h2>Alle Kategorien</h2>
<table>
	<c:forEach items="${categories}" var="category">
		<tr>
			<td>${category.categoryName}</td>
			<td><a
				href="controller?action=category&deleteCategory=<c:out value="${category.identifier}"/>">
					<img src="images/delete.png" alt="${category.categoryName} l�schen"
					title="${category.categoryName} l�schen">
			</a></td>
		</tr>
	</c:forEach>
</table>
<c:import url="footer.jsp" />