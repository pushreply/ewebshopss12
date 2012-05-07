<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Schl�sselworte" />
</c:import>

<h2>Neues Schl�sselwort hinzuf�gen</h2>
<form action="controller" method="post">
	<input type="text" name="addKeyword" value=""> <input
		type="hidden" name="action" value="keyword"> <input
		type="submit" value="Hinzuf�gen">
</form>

<h2>Alle Schl�sselworte</h2>
<table>
	<c:forEach items="${keywords}" var="keyword">
		<tr>
			<td>${keyword.keywordName}</td>
			<td><form action="controller" method="get">
					<input type="hidden" name="deleteKeyword"
						value="${keyword.identifier}"> <input type="hidden"
						name="action" value="keyword"> <input type="submit"
						value="Delete">
				</form></td>
		</tr>
	</c:forEach>
</table>

<c:import url="footer.jsp" />