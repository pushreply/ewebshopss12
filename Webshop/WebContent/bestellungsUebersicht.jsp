<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Alle Alben anzeigen" />
</c:import>


<h1>Bestellungsübersicht</h1>

<table border="1">
    <tr>
      <th>Albumtitel</th>
      <th>Artist</th>
      <th>Betrag</th>
    </tr>
 
    <tr>
      <td>1.Album</td>
      <td>1.Artist</td>
      <td>1.Betrag</td>
    </tr>
    <tr>
      <td>2.Album</td>
      <td>2.Artist</td>
      <td>2.Betrag</td>
    </tr>
    <tr>
      <td>3.Album</td>
      <td>4.Artist</td>
      <td>5.Betrag</td>
    </tr>
</table>

<c:import url="footer.jsp" />