<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Alle Alben anzeigen" />
</c:import>

 
<h2>Alle Aben</h2>
 
 <c:forEach items="${Alben}" var="album">
 <img src="images/bild.jpg" width=120 height=130 alt="bild">

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