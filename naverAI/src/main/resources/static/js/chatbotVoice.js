/**
 * chatbotVoice.js 
 */

$(function() {
	// 웰컴 메시지 받기 위해서 message 란에 입력 받기 전에
	// 빈 값으로 서버에 전송하고 웰컴 메시지 받음
	$('audio').hide();
	// 웰컴 메시지 출력하기 위해 파일명 없이 호출
	fileUpload(new Blob(), null);
	
	//-------------------------------------------------------------
	/* 음성 질문 녹음 */
	const record = document.getElementById("record");
    const stop = document.getElementById("stop");
    const soundClips = document.getElementById("sound-clips");

    const audioCtx = new(window.AudioContext || window.webkitAudioContext)(); // 오디오 컨텍스트 정의

    if (navigator.mediaDevices) {
        var constraints = {
            audio: true
        }
         let chunks = [];

        navigator.mediaDevices.getUserMedia(constraints)
            .then(stream => {
        const  mediaRecorder = new MediaRecorder(stream);
      
            record.onclick = () => {
                mediaRecorder.start();
                record.style.background = "red";
                record.style.color = "black";
            }

            stop.onclick = () => {	//정지 버튼 클릭 시
                mediaRecorder.stop(); //녹음 정지                       
                record.style.background = "";
                record.style.color = "";
            }
            
            mediaRecorder.onstop = e => {
                
                const clipName = "voiceMsg";
				//태그 3개 생성
                const clipContainer = document.createElement('article');                     
                //const audio = document.createElement('audio');
                const a = document.createElement('a');
				// 속성 컨텐츠 설정
                //clipContainer.classList.add('clip');
                //audio.setAttribute('controls', '');                        
                //clipContainer.appendChild(audio);
               
                clipContainer.appendChild(a);
                soundClips.appendChild(clipContainer);                        
				
                // chunks에 저장된 녹음 데이터를 audio 양식으로 설정
                //audio.controls = true;
                const blob = new Blob(chunks, {
                    'type': 'audio/mp3 codecs=opus'
                }); 
                
                chunks = [];
                const audioURL = URL.createObjectURL(blob);
                //audio.src = audioURL;
                //a.href=audio.src;
                //blob:http://localhost:8011/6377d19d-2ca8-49b1-a37f-068d602ceb60    
                a.href=audioURL;                   
                a.download = clipName;                      
                //a.innerHTML = "DOWN"
				a.click(); // 다운로드 폴더에 저장하도록 클릭 이벤트 발생		
				
				// 서버로 업로드: mp3 다운로드 시간 대기하므로 다운로드 후 1초 대기
				/*setTimeout(function(){
					fileUpload(clipName + ".mp3"); //파일명
				}, 1000);*/
				// 파일 다운로드 하지 않으니까 1초 대기할 필요 없음
                fileUpload(blob, clipName);
								
            } //mediaRecorder.onstop

            // 녹음 시작시킨 상태가 되면 chunks에 녹음 데이터를 저장하라 
            mediaRecorder.ondataavailable = e => {
                chunks.push(e.data)
            }
            
        })
        .catch(err => {
            console.log('The following error occurred: ' + err)
        })
}
	
	//-------------------------------------------------------------
	
	/* 서버에 업로드 */
	function fileUpload(blob, clipName){	    
		var formData = new FormData();
		formData.append('uploadFile', blob, clipName+".mp3");
		
    	// 녹음된 mp3파일 전송하고 반환된 텍스트(result)를 챗봇 서버에 전달
    	$.ajax({    		
			type:"post",	
			url: "chatbotOnlyVoice", // 통신할 url						
			data: formData, // 전송할 데이터 -> 파일명 : voiceMsg.mp3
			processData: false,
    		contentType: false,
			success: function(result) {					
				$('audio').prop("src", '/ai/'+ result)[0].play();
			},
			error: function(e) {
				alert("에러가 발생했습니다 : " + e);
			}
		});
	}
}); //  $(function() 끝
