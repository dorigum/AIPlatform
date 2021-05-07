<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>얼굴 감지</h2>
		
		<form id="fileUploadForm" method="post" action='<c:url value="clovaFace"/>'
			  enctype="multipart/form-data">
			파일 : <input type="file" id="uploadFile" name="uploadFile">
			<input type="submit" value="결과 확인">
		</form><br><br>
		
		<h2>얼굴 감지 결과</h2>
		<table border="1" width="800">
			<tr>
				<th>성별</th><th>성별 신뢰도</th>
				<th>나이</th><th>나이 신뢰도</th>
				<th>감정</th><th>감정 신뢰도</th>
				<th>포즈</th><th>포즈 신뢰도</th>
			</tr>
			
			<c:forEach items="${faceList }" var="f">
				
				<tr>
					<td>${f.gender }</td>
					<td><fmt:formatNumber value="${f.genderConf }" pattern="0.0000"/></td>
					<td>${f.age }</td>
					<td><fmt:formatNumber value="${f.ageConf }" pattern="0.0000"/></td>
					<td>${f.emotion }</td>
					<td><fmt:formatNumber value="${f.emotionConf }" pattern="0.0000"/></td>
					<td>${f.pose }</td>
					<td><fmt:formatNumber value="${f.poseConf }" pattern="0.0000"/></td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
		<a href="/">index 페이지로 이동</a>
	</body>
</html>