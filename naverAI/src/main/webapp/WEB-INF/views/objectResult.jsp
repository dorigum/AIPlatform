<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>객체 탐지</title>
		<script src="<c:url value='js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='js/object.js'/>"></script>
	</head>
	<body>
		<h2>객체 탐지</h2>
		
		<form id="objectForm" method="post" enctype="multipart/form-data">
			파일 : <input type="file" id="uploadFile" name="uploadFile">
			<input type="submit" value="결과 확인">
		</form><br><br>
		
		<h2>객체 탐지 결과를 이미지 위에 박스로 표시</h2>
		<canvas id="objectCanvas" width="600" height="600"></canvas>
		
		<div id = "resultDiv"></div>
		
		<br><br>
		<a href="/">index 페이지로 이동</a>
	</body>
</html>