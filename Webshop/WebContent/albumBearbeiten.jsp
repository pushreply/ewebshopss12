<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Album bearbeiten" />
</c:import>



<h3>Album  <c:out value="${album.albumTitel}"  default="N/A" /> bearbeiten</h3>

<img src="images/bild.jpg" width=120 height=130 alt="bild">

<form action="controller" method="get" enctype="multipart/form-data">
	
  <table">
    <tr>
      <td align="right">Titel:</td>
      <td><input type="text" name="titel" size="30" maxlength="30" value="<c:out value="${album.albumTitel}"  default="N/A" />"/></td>
    </tr>
    <tr>
      <td align="right">Artist:</td>
      <td><input type="text" name="artist" size="30" maxlength="40" value="<c:out value="${album.artist}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Anzahl der Disks:</td>
      <td><input type="text" name="diskAnzahl" size="30" maxlength="40" value="<c:out value="${album.numberOfDisks}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Preis:</td>
      <td><input type="text" name="preis" size="30" maxlength="40" value="<c:out value="${album.price}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Anzahl:</td>
      <td><input type="text" name="anzahl" size="30" maxlength="40" value="<c:out value="${album.amount}"  default="N/A" />" /></td>
    </tr>
    <tr>
      <td align="right">Anzahl der Tracks:</td>
      <td><input type="text" name="trackAnzahl" size="30" maxlength="40" value="<c:out value="${album.numberOfTracks}"  default="N/A" />" /></td>
    </tr>
    
    <tr>
      <td align="right">Label:</td>
      <td><input type="text" name="label" size="30" maxlength="40" value="<c:out value="${album.label}"  default="N/A" />" /></td>
    </tr>
  
    <tr>
      <td align="right"></td>
      <td>
         <input type="hidden" name="action" value="albumBearbeiten">
         <input type="submit" value="Speichern">
      </td>
    </tr>
  </table>
  <input type="hidden" name="ident1" size="30" maxlength="30" value="<c:out value="${album.identifier}"/>"/>
</form>

<c:import url="footer.jsp" />