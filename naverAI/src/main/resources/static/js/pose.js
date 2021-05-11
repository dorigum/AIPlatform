/**
 * pose.js
 */
 
 $(function(){
	$('#poseForm').on('submit', function(event){
		event.preventDefault(); // submit 후에 reload 안되게
		// alert($('#uploadFile').val()); // c:fakepath/run.jpg
		var src = $('#uploadFile').val().split("\\").pop(); // run.jpg
		// alert(src);
		var formData = new FormData($('#poseForm')[0]);
		
		$.ajax({
			type:"post",
			enctype:"multipart/form-data",
			url:"poseDetect",
			data:formData,
			processData:false, // 필수
			contentType:false, // 필수
			success:function(result){
				drawCanvas(result, src);
			},
			error:function(e){
				alert("에러 발생" + e);
			}
		});
	});
	
	function drawCanvas(result, src){
		var canvas = document.getElementById("poseCanvas");
		var context = canvas.getContext("2d");
		
		var poseImage = new Image();
		poseImage.src = "/ai/" + src;
		poseImage.width = canvas.width;
		poseImage.height = canvas.height;
		
		poseImage.onload = function(){
		context.drawImage(poseImage, 0, 0, poseImage.width, poseImage.height);
			
			var colors = ["red", "blue", "yellow", "yellow", "yellow", "green", "green", "green",
					      "skyblue", "skyblue", "skyblue", "black", "black", "black",
					      "pink", "gold", "orange", "brown"];
			
			var position = ["코", "목", "오른쪽 어깨", "오른쪽 팔굼치", "오른쪽 손목", "왼쪽 어깨", "왼쪽 팔굼치", "왼쪽 손목",
							"오른쪽 엉덩이", "오른쪽 무릎", "오른쪽 발목", "왼쪽 엉덩이", "왼쪽 무릎", "왼쪽 발목", "오른쪽 눈",
							"왼쪽 눈", "오른쪽 귀", "왼쪽 귀"];
												
			var values = ""; // (x, y) 좌표값 출력할 변수
			
			$.each(result, function(i){
				if(this.x !=0 || this.y !=0){
					context.strokeStyle = colors[i];
					context.strokeRect(this.x * poseImage.width, this.y * poseImage.height, 2, 2);
					var text = this.x.toFixed(2) + "," + this.y.toFixed(2); // x, y
					context.font = '10px serif';
					context.strokeText(text, this.x * poseImage.width, this.y * poseImage.height);
				}
				values += position[i] + "(" + this.x + "," + this.y + ") <br>";
			});
			
			$('#resultDiv').html(values);
		};
	}
});