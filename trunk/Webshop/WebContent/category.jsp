<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Kategorien" />
</c:import>

<h2>Neue Kategorie hinzufügen</h2>
<form action="controller" method="post">
	<input type="text" name="addCategory" value=""> <input
		type="hidden" name="action" value="category"> <input
		type="submit" value="Hinzufügen">
</form>

<h2>Alle Kategorien</h2>
<table>
	<c:forEach items="${categories}" var="category">
		<tr>
			<td>${category.categoryName}</td>
			<td><a
				href="controller?action=category&deleteCategory=<c:out value="${category.identifier}"/>">
					<img src="images/delete.png" alt="${category.categoryName} löschen"
					title="${category.categoryName} löschen">
			</a></td>
		</tr>
	</c:forEach>
</table>
<c:import url="footer.jsp" />