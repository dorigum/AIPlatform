/**
 * object.js
 */

$(function() {
	$('#objectForm').on('submit', function(event) {
		event.preventDefault(); // submit 후에 reload 안되게
		// alert($('#uploadFile').val()); // c:fakepath/run.jpg
		var src = $('#uploadFile').val().split("\\").pop();
		// alert(src);
		var formData = new FormData($('#objectForm')[0]);

		$.ajax({
			type: "post",
			enctype: "multipart/form-data",
			url: "objectDetect",
			data: formData,
			processData: false, // 필수
			contentType: false, // 필수
			success: function(result) {
				drawCanvas(result, src);
			},
			error: function(e) {
				alert("에러 발생" + e);
			}
		});
	});

	function drawCanvas(result, src) {
		var canvas = document.getElementById("objectCanvas");
		var context = canvas.getContext("2d");

		var objectImage = new Image();
		objectImage.src = "/ai/" + src;
		objectImage.width = canvas.width;
		objectImage.height = canvas.height;

		objectImage.onload = function() {
			context.drawImage(objectImage, 0, 0, objectImage.width, objectImage.height);

			$.each(result, function(i) {
				var x1 = this.x1 * objectImage.width;
				var y1 = this.y1 * objectImage.height;
				var x2 = this.x2 * objectImage.width;
				var y2 = this.y2 * objectImage.height;
				
				context.font = '15px batang';
				context.fillStyle = "rgb(255, 0, 255)";
				//context.fillText(this.name, x1, y1);
				
				context.strokeStyle = "red";
				context.lineWidth = 2;
				//context.strokeRect(x1, y1, x2-x1, y2-y1);
				
				context.fillText(this.name, y1, x1);
				context.strokeRect(y1, x1, y2-y1, x2-x1);
			});
		};
	}
});