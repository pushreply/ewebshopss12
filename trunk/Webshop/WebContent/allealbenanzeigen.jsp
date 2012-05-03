<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Alle Alben anzeigen" />
</c:import>

 
 
 <c:forEach items="${Alben}" var="album">
 <img src="images/bild.jpg" width=120 height=130 alt="bild">
 <!-- <td><img src="${album.coverpath}" width=120 height=130 alt="bild"> -->
 <table>
		<tr>
		  <td>Title:</td><td>${album.albumTitel}</td>
        </tr>
        <tr>						
				<td>
				   <form action="controller" method="post">
				        <input type="hidden"  name="action" value="albumAnzeigen">
						<input type="hidden"  name="identifier" value="${album.identifier}">
						<input type="submit"  value="album anzeigen">
					</form>
				</td>
		  </tr>
	</table>

  <input type=button onClick="location.href='index.jsp'" value='bearbeiten'>
  <input type=button onClick="location.href='trackhinzufuegen.jsp'" value='Track hinzufügen'>
  <div id="contecnt">
  </div>
  </c:forEach> 
<c:import url="footer.jsp" />