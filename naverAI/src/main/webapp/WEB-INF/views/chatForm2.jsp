<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>chatbot 채팅창</title>
		<script src="<c:url value='js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='js/chatbot2.js'/>"></script>
		<link rel="stylesheet" type="text/css" href="css/chatbot.css">
	</head>
	<body>
		<div id="wrap">
			<!-- Header -->
			<div id="chatHeader">
				<span>챗봇</span>
				<button id="btnClose">X</button>
			</div>
		
		<!-- 채팅 내용 출력 박스 -->
		<div id = "chatBox"></div>
		
		<!-- 질문 입력 폼 -->
		<div>
			<form id="chatForm" method="post">
			<input type="text" id="message" name="message" size="30" placeholder="질문을 입력하세요" autofocus>
			<input type="submit" id="btnSubmit" value="전송"><br><br>
			
			파일 : <input type="file" id="uploadFile" name="uploadFile">
			<input type="submit" value="결과 확인">

			</form>
		</form>
		</div><br>
		
		<button id="record">녹음</button>
		<button id="stop">정지</button>
		<div id="sound-clips"></div>
		
		<div>
			<audio preload="auto" controls></audio>
		</div>	
		
		<br><br>
		<a href="/">index 페이지로 이동</a>
		</div>
	</body>
</html>