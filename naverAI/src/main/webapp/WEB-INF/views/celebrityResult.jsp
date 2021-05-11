<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>유명인 얼굴 인식 결과</title>
	</head>
	<body>
		<h2>유명인 얼굴 인식</h2>
		
		<form id="fileUploadForm" method="post" action='<c:url value="clovaFaceCel"/>'
			  enctype="multipart/form-data">
			파일 : <input type="file" id="uploadFile" name="uploadFile">
			<input type="submit" value="결과 확인">
		</form><br><br>
		
		<h2>유명인 얼굴 인식 결과</h2>
		
		<table border="1" width="400">
			<tr><th>유명인</th><th>신뢰도</th>
			<c:forEach items="${celList }" var="cel">
				<tr><td>${cel.value }</td>
					<td><fmt:formatNumber value="${cel.confidence }" pattern="0.0000"/></td>
				</tr>
			</c:forEach>
		</table>
		
		<br><br>
		<a href="/">index 페이지로 이동</a>
		<img src="/ai/song.JPG">
	</body>
</html>