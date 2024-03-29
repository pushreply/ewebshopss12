<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="shop.dto.DBAlbum" %>
<%@ page import="java.util.LinkedList" %>

<c:import url="header.jsp">
	<c:param name="title" value="Warenkorb" />
</c:import>


<h1>Warenkorb</h1>

<jsp:useBean id="orderedAlben" scope="session" class="java.util.LinkedList" />
<jsp:useBean id="username" scope="session" class="java.lang.String" />

<c:choose>
       <c:when test="${empty sessionScope.orderedAlben}">
           <p>Ihr Warenkorb ist leer.</p>
       </c:when>
    <c:otherwise>
    
<table border="1">
    <tr>
      <th>Titel</th>
      <th>Artist</th>
      <th>Anzahl</th>
      <th>Sr�ckpreis</th>
      <th>Preis</th>
      <th>Artikel entferen</th>
    </tr>
    
    <c:forEach items="${sessionScope.orderedAlben}" var="album">

    <tr>
      <td><c:out value="${album.albumTitel}" /></td>
      <td><c:out value="${album.artist}" /></td>
      <td><c:out value="${album.amount}" /></td>
      <td><c:out value="${album.price}" /></td>
      <td><c:out value="${album.price * album.amount}" /></td>
      <td>
          <form action="controller" method="get">
             <input type="hidden" name="action" value="orderalbum"> 
			 <input type="hidden" name="DeleteAlbumFromSessionID" value="${album.identifier}"> 
			 <input class="delbutton" type="submit" value="L�schen">
		  </form>
      </td>
    </tr>
 </c:forEach>
 
</table>

</c:otherwise>
</c:choose>

<br>
<p>Gesamtbetrag:<c:out value = "${gesammtpreis}" /></p>
<br>

<form action="controller" method="get">
   <input type="hidden" name="action" value="orderalbum"> 
   <input type="hidden" name="weiterealben" value="alben"> 
   <input class="userbutton" type="submit" value="Weitere Alben bestellen">
</form>
<p>
<c:choose>
   <c:when test="${not empty sessionScope.username}">
     <form action="controller" method="get">
             <input type="hidden" name="action" value="orderalbum"> 
			 <input type="hidden" name="order" value="alben"> 
			 <input class="userbutton" type="submit" value="Bestellen">
     </form>
  </c:when>
  <c:otherwise>
      <p>Um die Bestellung aufzugeben, melden sie sich bitte an!</p>
   </c:otherwise>
</c:choose>
<c:import url="footer.jsp" />