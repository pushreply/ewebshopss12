<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="Alle Alben anzeigen" />
</c:import>


<h1>Alle Alben</h1>
<div id="albumlist">
	<c:forEach items="${Alben}" var="album">
		<div class="album-item">
			<a href="controller?action=album&show=<c:out value="${album.identifier}"/>">
				<c:if test="${empty album.cover}">
					<img src="images/bild.jpg" width=120 height=130
						alt="Kein Bild vorhanden" title="Kein Bild vorhanden">
				</c:if> 
				<c:if test="${ not empty album.cover}">
					<img src="imageDisplayProcess?identifier=<c:out value="${album.identifier}"/>"
						width=120 height=130 alt="${album.albumTitel} - ${album.artist}"
						title="${album.albumTitel} - ${album.artist}" />
				</c:if> 
				<br />
				${album.albumTitel}
			</a>
		</div>
		<!--  /class="album-item" -->
	</c:forEach>
</div>
<!-- /id="albumlist" -->
<c:import url="footer.jsp" />