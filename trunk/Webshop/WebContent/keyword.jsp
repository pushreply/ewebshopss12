<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Schlüsselworte" />
</c:import>
<c:choose>
	<c:when test="${isAdmin=='true'}">
		<h2>Neues Schlüsselwort hinzufügen</h2>
		<form action="controller" method="post">
			<input type="text" name="addKeyword" value=""> <input
				type="hidden" name="action" value="keyword"> <input
				type="submit" value="Hinzufügen">
		</form>
	</c:when>
</c:choose>
<h2>Alle Schlüsselworte</h2>
<table>
	<c:forEach items="${keywords}" var="keyword">
		<tr>
			<td>${keyword.keywordName}</td>
			<td><a
				href="controller?action=keyword&deleteKeyword=<c:out value="${keyword.identifier}"/>">
					<c:choose>
						<c:when test="${isAdmin=='true'}">
							<img src="images/delete.png" alt="${keyword.keywordName} löschen"
								title="${keyword.keywordName} löschen">
						</c:when>
					</c:choose>
			</a></td>
		</tr>
	</c:forEach>
</table>

<c:import url="footer.jsp" />