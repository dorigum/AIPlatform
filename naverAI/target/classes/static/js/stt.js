/**
 * stt.js
 */
 
 $(function(){
	$('#sttForm').on('submit', function(event){
		event.preventDefault(); // submit 후에 reload 안되게
		var formData = new FormData($('#sttForm')[0]);
		var src = $('#uploadFile').val().split("\\").pop(); // kor1.mp3 파일명
		
		$('audio').prop("src", '/ai/' + src);
		
		$.ajax({
			type:"post",
			enctype:"multipart/form-data",
			url:"clovaSTT",
			data:formData,
			processData:false, // 필수
			contentType:false, // 필수
			success:function(result){
				$('#resultDiv').text(result);
			},
			error:function(e){
				alert("에러 발생" + e);
			}
		});
	});
});