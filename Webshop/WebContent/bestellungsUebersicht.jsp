<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="shop.dto.DBAlbum" %>
<%@ page import="java.util.LinkedList" %>

<c:import url="header.jsp">
	<c:param name="title" value="Alle Alben anzeigen" />
</c:import>


<h1>Bestellungsübersicht</h1>

<jsp:useBean id="orderedAlben" scope="session" class="java.util.LinkedList" />

<table border="1">
    <tr>
      <th>Albumtitel</th>
      <th>Artist</th>
      <th>Betrag</th>
    </tr>
    <%! int betrag = 0; %>
    <c:forEach items="${sessionScope.orderedAlben}" var="album">

    <tr>
      <td><c:out value="${album.albumTitel}" /></td>
      <td><c:out value="${album.artist}" /></td>
      <td><c:out value="${album.price}" /></td>
    </tr>
    </c:forEach>
    
    <tr>
      <td></td>
      <td align="right"><Strong>Gesammtbetrag</Strong></td>
      <td></td>
    </tr>
</table>
<br>

<input type="button" value="Weitere Alben bestellen" onClick="history.back()">

<c:import url="footer.jsp" />