<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Alle Alben anzeigen" />
</c:import>

 
<h2>Alle Alben</h2>
 
 <c:forEach items="${Alben}" var="album">
 <c:if test="${empty album.cover}">
       <img src="images/bild.jpg" width=120 height=130 alt="Kein Bild vorhanden" title="Kein Bild vorhanden">
 </c:if>
 <c:if test="${ not empty album.cover}">
       <img src="imageDisplayProcess?identifier=<c:out value="${album.identifier}"/>"  width=120 height=130 alt="${album.albumTitel} - ${album.artist}" title="${album.albumTitel} - ${album.artist}"/>
 </c:if>

 <table>
		<tr>
		  <td>Title:</td><td>${album.albumTitel}</td>
        </tr>
        <tr>						
				<td>
				   <form action="controller" method="get">
				        <input type="hidden"  name="action" value="albumAnzeigen">
						<input type="hidden"  name="identifier" value="${album.identifier}">
						<input type="submit"  value="album anzeigen">
					</form>
				</td>
				
		  </tr>
	</table>

  <hr/>
  </c:forEach> 
<c:import url="footer.jsp" />