<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table width="589" border="1">
		<c:forEach items="${AlbumTracks}" var="track">
			<tr>
				<td>${track.trackNumber}</td>
				<td>${track.trackTitle}</td>
				<td>${track.trackArtist}</td>
				<td>${track.trackDate}</td>
				<td><label for="${track.trackID}">Play</label>

					<form action="loadtrack" method="get">
						<input type="submit" name="trackID" value="${track.trackID}"
							id="${track.trackID}">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>