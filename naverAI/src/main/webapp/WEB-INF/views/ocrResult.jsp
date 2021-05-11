<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>OCR</title>
		<script src="<c:url value='js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='js/ocr.js'/>"></script>
	</head>
	<body>
		<h2>OCR : 이미지에서 텍스트 추출</h2>
		
		<form id="ocrForm" method="post" enctype="multipart/form-data">
			파일 : <input type="file" id="uploadFile" name="uploadFile">
			<input type="submit" value="결과 확인">
		</form><br><br>
		
		<h2>OCR : 이미지에서 텍스트 추출 결과</h2>
		<div id = "resultDiv"></div>
		
		<br><br>
		<a href="/">index 페이지로 이동</a>
	</body>
</html>