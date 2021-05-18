/**
 * chatbot.js
 */
 
 $(function(){
	// 웰컴 메시지 받기 위해서 message 란에 입력 받기 전에
	// 빈 값으로 서버에 전송하고 웰컴 메시지 받음
	callAjax(); // message 값 없이 서버로 전송
	
	$('#chatForm').on('submit', function(event){
		event.preventDefault(); // submit 후에 reload 안되게
		
		/* chatBox에 보낸 메시지 추가 (동적 요소 추가) */ /* 넌 누구니? */
		$('#chatBox').append('<div class="msgBox send"><span>' +
							$('#message').val() + '</span></div><br>');
		
		callAjax();
		
		/* 입력란 비우기*/
		$('#message').val('');
		
	}); // submit 끝
	
	function callAjax(){
		$.ajax({
			type:"post",
			url:"chatbotCall",
			data:{message:$('#message').val()},
			success:function(result){
				/* chatBox에 받은 메시지 출력 (챗봇의 답변) */
				$('#chatBox').append('<div class="msgBox receive">챗봇<br><span>' +
							result + '</span></div><br><br>');
							
			/* 스크롤해서 올리기 */
			$('#chatBox').scrollTop($('#chatBox').prop("scrollHeight"));
			
			},
			error:function(e){
				alert("에러 발생 : " + e);
			}
		});
	}
	
}); //  $(function() 끝