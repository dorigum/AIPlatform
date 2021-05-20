<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>chatbot 음성 채팅</title>
		<script src="<c:url value='js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='js/chatbotVoice.js'/>"></script>
		<link rel="stylesheet" type="text/css" href="css/chatbot.css">
	</head>
	<body>
		음성 메시지 : <button id="record">녹음</button>
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