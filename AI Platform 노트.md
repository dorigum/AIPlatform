### 스프링 프로젝트 생성

* `Naver AI Platform`에서 `Application` 등록
* 인증키 받아서 `API`를 통해 결과 받아 `JSP` 뷰페이지로 출력

* 유명인 얼굴 인식



### 스프링 부트 프로젝트 생성

* 프로젝트명 : naverAI
* 패키지명 : com.multi.naverai
* `Dependencies`
  * `JDBC API` / `Oracle Driver` / `MyBatis Framework`
  * `Spring Web`



* `application.properties` 파일에 설정 추가
* `pom.xml`에 의존성 추가 - 5개
  * `jsp`
  * `devtools`
  * `json`



* `views` 폴더 생성
* `src/main/webapp` 폴더에 `WEB-INF` / `views` 폴더 추가



* `index.jsp` 생성
  * 각 `AI` 서비스를 사용하기 위한 메뉴 항목 하나씩 추가
  * 컨트롤러에게 요청 -> 각 서비스마다 `Service` 클래스 생성해서 처리 -> `Naver AI API` 요청
  * 컨트롤러에서 뷰 페이지로 출력 <- 결과를 컨트롤러에게 반환 <- `Naver AI API` 결과 받음



* 패키지 생성 : `com.multi.aiservice`
* 컨트롤러 생성 : `APIController`



----

* `Naver AI Platform` 접속
* `Application` 등록
* 인증키 발급 (코드에서 사용)



* 개발 가이드 : https://api.ncloud-docs.com/docs/ai-naver-clovafacerecognition-celebrity

* 유명인 얼굴 인식 `API` 개요

* 감지된 얼굴의 수

* 감지된 얼굴을 분석한 정보

  * 닮은 유명인 이름
  * 해당 유명인물 닮은 정도

  

* 요청 `URL` / 헤더 / 응답 필드 확인

* 요청 예시

* 응답 예시 : `JSON` 형식

  * `key : faces :`
  * `value` : `[ ]` 리스트로 구성
  * `faces`를 추출해서 리스트를 반복 (`for`문)하여 `value` 와 `confidence` 추출

  

* `API` 예제 : `Java` 부분을 복사해서 사용할 것임

* 변경할 내용

  * `clientID` / `clientSecret`
    * `uploadFile` : 파일 경로 및 파일명

* 출력 : `JSON` 형식의 문자열로 콘솔창에 출력



* 이미지를 복사해서 다음 디렉터리에 저장
* `c:/ai/xxx.jpg` : 1인 (여성/ 남성/ 연령별)
* 2인 / 5인 구성된 이미지 준비



#### 작성 클래스

* `CelebrityVO`

* `CFRCelebrityService`

* `APIController` (이미 작성했음)

* `index.jsp`에 링크 추가 (요청 맵핑)

* 실행시켜서 콘솔 창에 결과 출력

  * ```
    {"info":{"size":{"width":144,"height":144},"faceCount":1},"faces":[{"celebrity":{"value":"강동원","confidence":0.619254}}]}
    ```

  * `JSON` 형식의 문자열로 출력

  

* 우리가 추출할 내용

  * "faces"
  * "celebrity"
  * "value" / "confidence"
  * "강동원" / 0.619254



* `JSON` 형식의 문자열에서 "`confidence`" 와 "`value`" 추출해서
* `CelebrityVO`에 저장
* 이 작업을 수행할 메소드 추가 : 서비스 클래스
* `CFRCelebrityService` 클래스에 `jsonToVoList()` 메소드 추가
* 결과로 받은 `JSON` 형식의 문자열을 전달받아서
* `confidence`와 `value`를 추출해서
* `CelebrityVO` 에 저장하고 리스트에 추가
* `CelebrityVO` 리스트를 `clovaFaceRecogCel()` 메소드에게 반환
* 그러면 `clovaFaceRecogCel()` 는 이 결과 리스트를 컨트롤러에게 반환
* 컨트롤러는 뷰페이지에 출력



* `clovaFaceRecogCel()` 메소드 호출



----

* `celebrityResult.jsp` 페이지 윗부분에 (제목 위에) 파일 업로드 기능 추가

* 파일을 선택하면 -> 서버 (`c:/ai/`폴더로 업로드)

  * `AI API` 호출할 때 `ai` 경로로 지정해서 서버에서 파일을 읽어서 전송할 수 있도록 기능 추가

  

----

#### 얼굴 감지

> 입력받은 이미지로부터 얼굴을 감지하고 입력된 이미지에서 얼마나 많은 얼굴이 감지되었고, 각 얼굴이 어디에 어떤 크기로 위치하며 어떤 모습을 하고 있는지 반환



- 감지된 얼굴의 수
- 감지된 각 얼굴을 분석한 정보
  - 감지된 각 얼굴의 좌표 및 크기
  - 감지된 각 얼굴의 눈, 코, 입의 좌표
  - 감지된 얼굴의 추정 성별 및 추정치
  - 감지된 얼굴의 추정 나이 및 추정치
  - 감지된 얼굴에서 분석된 감정
  - 감지된 얼굴의 방향



(1) `API` 호출해서 콘솔창에 결과 출력

* 서비스 클래스 추가 : `CFRService`

  * 메소드 추가 (`API` 호출해서 결과 받아서 콘솔창에 출력하는 메소드) : `clovaFaceRecog()`

* 컨트롤러에 추가 : 서비스의 메소드 호출

* `faceRecogResult.jsp`

  * `faceRecogResult` 페이지
  * `index` 페이지로 이동

  

(2) 

```
"faces": [{
   "roi": {
     "x": 235,
     "y": 227,
     "width": 326,
     "height": 326
   },
   "age": {
     "value": "22~26",
     "confidence": 0.742265
   },
   "emotion": {
     "value": "simile",
     "confidence": 0.460465
   },
   "pose": {
     "value": "frontal_face",
     "confidence": 0.937789
   }
 }]
}
```



------

## OCR 실습 (Text OCR)

* `OCR API` : https://api.ncloud-docs.com/docs/ai-application-service-ocr-ocr

(1) 기본 : 서비스 `API` 호출 결과를 `JSON`형식 문자열로 출력

 * `OCRService` 클래스 생성 : `clovaOCRService()` 메소드 추가
    * `API` 코드를 `clovaOCRService()` 메소드에 붙여넣기
    * `writeMultiPart()` 메소드는 전체 복사해서 `clovaOCRService()` 메소드 아래에 붙여 넣기
      (즉, `OCRService` 클래스에 메소드 2개가 됨)
 * `APIController`에 추가
 * `index.jsp`에 링크 추가
 * 실행해서 결과 출력 : 콘솔 창에서 확인
 * `movie` 이미지 다른 폴더에 저장 (실행 시 `c:/ai` 폴더로 업로드)



(2) `REST API` `@RestController` 추가

 * 파일 업로드
 * `Ajax`로 파일을 서버로 전달하고 결과 받아서 `view` 페이지 출력
 * `ocrResult.jsp` (파일 업로드)
 * `ocr.js` (`Ajax`)
 * `AIResultController` (`@RestController`) : 컨트롤러 추가
 * `@ComponentScan` (`AIResultController`) 추가
 * 서비스 메소드 변경 (파일 이름 전달 받음)
 * `APIController` 수정
 * `index.jsp` 수정
 * 실행 : 텍스트가 있는 이미지 파일을 업로드
 * 결과 : 텍스트 출력



----

## Pose Estimation (포즈 인식)

(1) 기본 : 서비스 `API` 호출 결과를 `JSON` 형식 문자열로 출력

* `PoseEstimationService` 클래스 생성 : `poseEstimate()` 메소드 추가
  * `API` 코드를 `poseEstimate()` 메소드에 붙여넣기
* `APIController`에 추가
* `index.jsp`에 링크 추가
* 이미지 : `c:/ai/run.jpg`
* 실행해서 결과 출력 : 콘솔창에서 확인



(2) `REST API` `@RestController` 사용

* `PoseVO` 생성 : `index`, `x`, `y`
* `jsonToVOList()` 메소드 추가
* 서비스 클래스 / 메소드 수정
* `AIResultController` (`@RestController`) 에 추가
* `poseResult.jsp` 생성 : 파일 업로드 / 결과 출력
* `pose.js (Ajax)` : `formData` 보내고 / `result` 받고
* (`drawCanvas` : 이미지 위에 추출한 좌표 (0~17)의 표시)
* `index.jsp`에 추가
* `APIController` 변경
* 결과 확인



* `resultDiv`에 출력
  * 코 (0.356433, 02399328)
  * 목 (0.356433, 02399328)



-----

## Object Detection (객체 탐지)

(1) 기본 : 서비스 `API` 호출 결과를 `JSON` 형식 문자열로 출력

* `ObjectDetectionService` 클래스 생성 : `objectDetect()` 메소드 추가
  * `API` 코드 복사
* `APIController`에 추가
* `index.jsp`에 링크 추가
* 이미지 : `c:/ai/animal2.jpg`
* 실행해서 결과 출력 : 콘솔창에서 확인



(2) `REST API` `@RestController` 사용

* `ObjectVO` : `name`, `x1`, `y1`, `x2`, `y2`
* `ObjectDetectionService` 클래스에 `jsonToVoList()` 메소드 추가
  * `name`, `x1`, `y1`, `x2`, `y2` 값 추출해서 `VO`에 담아서 `List` 반환
* `objectResult.jsp` 생성 : 파일 업로드 / 결과 출력
* `object.js` (`Ajax`) : `formData` 보내고 / `result` 받고
* (`drawCanvas` : 이미지 위의 탐지한 객체에 바운딩 박스 표시 (사각형 표시))
* `index.jsp` (-> `APIController`)
* `APIController` 변경
* 결과 확인 : 사각형 그리기 어려우면 이 부분 하지 말고
  * `name`, `x1`, `y1`, `x2`, `y2` 출력



----

## CLOVA Speech Recognition (CSR)

* 음성 데이터를 인식해서 텍스트로 변환하여 전달

* `mp3` 파일 전송해서 결과 텍스트 받아서

* 브라우저에 텍스트 출력, 파일로 저장

* 음성 인식에 사용할 언어 (요청 파라미터)

  * `Kor` : 국어
  * `Jpn` : 일본어
  * `Chn` : 중국어
  * `Eng` : 영어

  

* 응답 : 텍스트 (`String`)



#### CSR (Speech-To-Text : STT)

(1) 기본 : 서비스 `API` 호출 결과를 `JSON` 형식 문자열로 출력

 * `STTServiece` 클래스 생성 : `clovaSpeechToText()` 메소드 추가
   	* `API` 코드 복사
	* `APIController`에 추가
	* `index.jsp`에 링크 추가
	* 실행해서 결과 출력 : 콘솔창에서 확인



(2) `REST API` `@RestController` 사용

* `jsonToString()` 메소드 추가 : 텍스트 추출
* `STTServiece` 클래스 변경
* `AIRestController` 에 추가
* `APIController` 변경
* `sttResult.jsp` 생성
  * 파일 업로드 추가
  * 추출된 텍스트 추출
* `stt.js` 생성 (`Ajax`)
* `index.jsp` 에 링크 추가
* 결과 확인
  * 음성 파일(`mp3`) 업로드
  * 음성을 텍스트로 변환된 결과 출력
    * 텍스트 출력



(3) 추가 기능

* 언어 종류 전달 기능 추가
* 출력된 텍스트와 일치하는지 확인하기 위해 `<audio>` 태그로 플레이하는 기능 추가
* 결과로 받은 텍스트를 파일로 저장하는 기능 추가



*  `<audio>` 태그 추가해서 업로드한 `mp3` 파일 플레이
* `sttResult.jsp` 에  `<audio>` 태그 추가
* `stt.js` 에서  `<audio>` 의 `src` 속성 설정 (업로드한 파일로 설정)



* 언어 종류 전달 기능 추가
* `sttResult.jsp` `<form>` 태그 안에 `<select>` 태그 추가
  * `stt.js` 변경 필요 없음 (formData 안에 다 포함되서 전송)
  * `@RequestParam()` 추가하면 됨
* 서비스 코드 변경



* 결과로 받은 텍스트를 파일로 저장하는 기능 추가
* 추출된 텍스트를 파일로 저장하는 메소드 추가
  * `resultToFileSave()` 메소드에서 파일 저장 기능 구현
* 저장 디렉터리 : `c:/ai` 폴더에 저장



----

## CLOVA Voice

* 네이버 가이드에 `tts` (`Text-To-Speech`)가 3가지가 있는데
* 1개만 되고 나머지는 사용할 수 없음



* 우리가 사용할 수 있는 서비스
  * `CLOVA Voice` : `TTS` (`Premium`)

* 주의!!!!

  * `String apiURL` = http://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts

  

* 텍스트를 파라미터로 입력 받아 음성을 합성하여 그 결과를 반환

  * `text` -> `mp3`, `wav` 반환

  

* 서비스 신청 : `CLOVA Voice-Premium`



### TTS (Text-To-Speech)

(1) 기본 : 서비스 `API` 호출 결과를 `mp3` 파일로 반환

* `TTSService` 클래스 생성 : `clovaTextToSpeech()` 메소드 추가
  * `API` 코드 복사
* `APIController`에 추가
* `index.jsp`에 링크 추가
* 실행해서 결과 출력 : 반환된 `mp3` 파일 확인



(2) 언어 / 목소리 변경

* 저장 위치 변경



(3) 파일 업로드

* 텍스트 파일 업로드
* 파일 내용을 읽어서 텍스트로 추출해 서버로 전송
  * 텍스트 파일 읽어서 문자열 반환하는 메소드 추가 : `fileRead()`
* `REST API` : `@RestController` 사용



(4) `<audio>` 태그 추가

(5) 언어 선택 `<select>` 태그 추가



- nara : 한국어, 여성 음색
- jinho : 한국어, 남성 음색
- nhajun :한국어, 아동 음색 (남)
- ndain : 한국어, 아동음색 (여)
- clara : 영어, 여성 음색
- matt : 영어, 남성 음색
- carmen : 스페인어, 여성 음색
- shinji : 일본어, 남성 음색
- meimei : 메이메이 : 중국어, 여성 음색



- nara : 나라 : 한국어, 여성 음색
- jinho : 진호 : 한국어, 남성 음색
- nhajun : 하준 : 한국어, 아동 음색 (남)
- ndain : 다인 : 한국어, 아동음색 (여)
- clara : 클라라 : 영어, 여성 음색
- matt : 매트 : 영어, 남성 음색
- carmen : 카르멘 : 스페인어, 여성 음색
- shinji : 신지 : 일본어, 남성 음색



- meimei : 메이메이 : 중국어, 여성 음색

见到你很高兴!



---

## CLOVA Chatbot 서비스 만들기

### 1단계

1. 도메인 생성 (1명만)
2. 대화 생성
3. 학습 질문 입력하기
4. 챗봇 답변 입력하기
5. 챗봇 빌드하기 - 빌드 중에 메세지 출력
   * 빌드 중에 메세지 출력
   * '베타 환경에서 테스트가 가능합니다'
   * 아래 테스트 버튼 눌러스 확인
6. 챗봇 테스트



* 주의사항!
  * `Basic` 서비스 타입 사용
  * 하나의 도메인 당 빌드 10회까지 가능
  * 빌드 10회 하고 나면
  * 새로운 도메인 생성하지 말고 기존 도메인 복사해서 사용 (또 다시 10회 빌드 가능)



-----------------------

### 2단계 : 빌드 후에 Web에서 챗봇을 구현하는 과정

1. 서비스 배포 : 우측 상단에 있는 [서비스 배포] 클릭 (빌드 ID 확인)
2. 메신저 연동 : `CLOVA Chatbot Custom` 연동 설정
   * 왼쪽 상단 메뉴에서 [빌드 내역] 클릭하고
   * 두번째 탭 [메신저 연동] 선택 ->
   * `Custom API Gateway`와 `End-point` 연결이 필요합니다.
   * [자동 연동] 버튼 클릭 -> [확인]
   * `API Gateway invoke URL` : [주소 복사]
   * 아래로 내려서 [메신저 연동 설정] : `Secret Key` [생성] [복사]
3. 키 생성 복사 : `API Gateway` 호출 `URL` 생성하고 `Secret Key` 발급



--------------------------

### 3단계 : 스프링에서 작업

​	[#1]

1. `ChatbotService` 클래스 생성

2. `API` 코드 복사 : 메소드 3개 다 복사
   
   * 인코딩 부분 오류 : 수정된 코드 보내줌
   
     ```java
     //encodeBase64String = Base64.encodeToString(rawHmac, Base64.NO_WRAP);
     encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
     ```
   
3. `APIController`에서 서비스의 `main()` 메소드 호출하면서 질문 전달
   
   * "넌 누구니?"
   
4. 콘솔 창에서 결과 확인 (`JSON` 형식의 문자열 반환)

   

   [#2]

5. "답변 추출" `jsonToString()` 메소드 추가 : 텍스트

6. `REST API` - `AIRestController` 추가

7. `chatbotResult.jsp` (간단한 채팅창)

   * `<input>` 태그에서 질문 입력
   * `<div id = "resultDiv">`

8. `chatbot.js` (`Ajax`)

9. `index.jsp`



#### 결과 확인

* 입력 란에 질문 입력 : "넌 누구니?"
* `div`에 바로 결과 출력 : "저는 독서 지도사 입니다."



----

[#3]

### 채팅창 만들기 (채팅창 꾸미기)

* 질문 / 답변 출력
* 스크롤
* css



----

[#4]

### 음성으로 질문하면 챗봇에서 텍스트로 답변 보내기 (채팅창에 텍스트도 출력) + 음성

(1) 녹음 기능 : 웹 페이지에서 녹음

 * [녹음] 버튼 : 음성 녹음하고 `mp3` 파일로 저장 (다운로드 폴더에 저장)
   	* 너는 누구니?
 * [중지] 버튼 : 챗봇에서 응답
   	* 저는 독서 지도사입니다.



(2) `STT` : `Speech To Text`

* `STT` 기능을 사용해서 다운로드 폴더에 저장된 음성 파일을 텍스트로 변환
* 변환된 텍스트를 챗봇에게 질문으로 전달
* 챗봇이 텍스트로 답변 전달 -> 채팅창에 출력



* 질문 녹음 : 다운로드 폴더에 저장
* 음성 파일을 텍스트로 변환 : `STT`
* `mp3` 파일명 전달
* 다운로드 폴더에 있는 파일을 서버로 전송
* `STT` : 텍스트 변환
* 변환된 텍스트를 질문으로 챗봇에게 전달
* 챗봇으로부터 답변 받아서 채팅창에 출력



* `chatForm.jsp`에 녹음 버튼 추가
* `chatbot.js`
  * 녹음 기능
  * 파일 업로드: `STT` (`clovaSTT2`)
  * 챗봇에게 질문 전달하고 답변 받기
  * 채팅창에 출력
* `AIRestController`에 추가 : `clovaSTT2`
* `STTService` 클래스에 메소드 추가 : `chatbotSpeechToText()` : `Kor`
  * 파일 경로 이름 전달 : `filePathName`
  * 텍스트로 변환된 녹음 파일은 삭제 (다운로드에 있는 음성 파일 삭제) : `voiceFile.delete()`
* 결과 확인
  * 음성으로 질문 (녹음)하고 텍스트로 답변 받기
  * 음성 질문 내용/답변도 채팅창에 출력



----

[#5]

### 음성으로 질문하면 음성으로 답변하기 (텍스트 답변 -> 음성 파일 변환 : TTS)

* 챗봇으로부터 받은 답변을 `TTS` 기능을 사용해서 음성으로 변환하여 출력
* 받은 답변을 `TTS` 전달하기 위한 Ajax 추가
  * `callAjaxTTS()` 함수 추가
  * 텍스트 `message` 전달하고
  * 답변 받으면 `<audio>` 플레이 (동적 생성하지 말고 `jsp` 에 `<audio>` 태그 배치 (나중에 숨기기))



* `TTSService` 클래스에 메소드 추가
  * `chatbotTextToSpeech`(`String message`)
  * 파일 이름이 아닌 메시지 전달



* `chatForm.jsp` 에 `<audio>` 태그 추가
* `hide()` 사용해서 숨기기



* `chatbot.js`에 추가 (`callAjaxTTS()` 함수)
* `TTSService` 클래스에 메소드 추가
* `AIRestController` 에 추가 : `chatbotTTS`
* 결과 확인



---

[#6]

### 채팅창 없이 음성 채팅 (음성 질문 -> 음성 답변)

* 중간에 클라이언트 출력 없고 서버에서 한번에 다 처리하고 결과만 클라이언트로 전송



* `chatbotVoice.jsp` 새로 생성
  * 채팅창 없음
  * 음성 메시지 녹음
  * `<audio>` 태그



* `chatbot.js`
  * `fileUpload()`
  * url : `chatbotOnlyVoice`
  * `<audio>` 플레이만
  * `chatBox` 삭제



* `AIRestController`
  * 모두 전달하고 받음
  * `STT` -> `chatbot` -> `TTS`



* `index.jsp` 에 추가
* 결과 확인



* 웰컴 메시지 출력되도록 추가
* `chatbotVoice.jsp`
  * 파일명 없이 `fileUpload("");`



* `AIRestController`
  * `result = ""` 부분 추가



-----

### 대화 모델 연습

* 이미지 답변
* 멀티링크 답변
* `jsonToString()` 에서 타입 별로 추출



#### API 코드 복사 경로

* `Console  / AI NAVER API / Application / 개발 가이드`
* `AI Application Service / CLOVA Chatbot / CLOVA Chatbot 고급 기능 상세 가이드 / CLOVA Chatbot Custom API`
* 바로 가기 : `CLOVA Chatbot Custom API`
* `CLOVA Chatbot Custom API` 클릭하고 오른쪽 페이지에서 `[각 언어별 CLOVA Custom API 구현 예제]`
* https://api.ncloud-docs.com/docs/ai-application-service-chatbot-chatbot
* 메소드 3개 복사

```java
public static String main(String voiceMessage, String apiURL, String secretKey) {}
```

```java
public static String main(String voiceMessage) {
		String apiURL = "";
		String secretKey = "";
}
```



----

### 웹 페이지에서 음성 녹음 기능

* `voiceRecord.jsp`
* 녹음 / 정지 버튼
* 녹음 : 음성 녹음
* 정지 : 파일로 저장 (다운로드 폴더)



----

### 독서 모임 시나리오

```
대화 : 독서 모임 신청하기

주최 : 북클럽 멀티
일자 : 2021-05-22 토
시간 : 15:00 ~17:00
소요 시간 : 2시간
장소 : 멀티캠퍼스 본관 512호
대상 도서 : 지구의 눈물
참가비 : 5,000원 (음료)
찾아 오는 길 : 이미지 https://www.multicampus.com/cs/map/mapMain?p_menu=MzY0I0dMT0JBTA==&p_gubun=Qw==
주차 안내 : 이미지
문의 : 010-1234-1234
이메일 : mult@book.com
신청 : https://www.multicampus.com/kr/index.html
```



-----

### 이미지 / 멀티링크 답변 추가

#### 1. 기본 답변 : 독서 모임 시간 문의

#### 2. 이미지 답변

* 이미지만 : 약도
* 이미지 + 텍스트 : 찾아 오는 길



변경된 빌드->빌드->서비스 배포->연동



----

### MySQL 다운로드 및 설치

* 사용자 생성
* 테이블 생성
* 스프링 부트 프로젝트에서
  * `dependency` 추가
  * `DB` 연동 설정
  * 시간대 설정 변경



* https://www.mysql.com/
  * MySQL Community (GPL) Downloads »
  * MySQL Community Server
  * No thanks, just start my download.
  * 3306



* 스키마 생성 : `springdb` (소문자)
* 사용자 생성 : `Users and Privileges`
* `Connection`



### 오라클 vs MySQL 문법 차이

* 시간 설정
* `시퀀스`
* `Rownum`



----

## 일반적인 데이터베이스 구조 - 4계층

* 인스턴스 - 데이터베이스 (여러 개) - 스키마 (여러 개) - 테이블 (여러 개)
  * (인스턴스 : DMS 실행 단위 (`프로세스` / `DB 서버`))
  * 스키마 : 데이터베이스 구조와 제약조건을 명시한 것



## 오라클 데이터베이스 구조 - 4계층

* 인스턴스 - 데이터베이스 (1개) - 스키마 (여러 개) - 테이블 (여러 개)



## MySQL 데이터베이스 구조 - 3계층

* 인스턴스 - 스키마 (여러 개) - 테이블 (여러 개)
  * 데이터베이스 없음 : 데이터베이스와 스키마가 동일한 의미로 사용



```sql
CREATE TABLE product (
            prdNo VARCHAR(10) NOT NULL PRIMARY KEY,
            prdName VARCHAR(30) ,
            prdPrice INT(10),           
            prdCompany VARCHAR(50),
            prdStock INT(5)
         );        
         
  INSERT INTO product VALUES('1001', '세탁기', 100000, '삼성', 10);    
  INSERT INTO product VALUES('1002', '냉장고', 120000, 'LG', 3);
  INSERT INTO product VALUES('1003', '프린터', 300000, 'HP', 5);    
    
  SELECT * FROM product;

```



```
SELECT @@GLOBAL.time_zone, @@SESSION.time_zone;
```

```
SET GLOBAL time_zone='+9:00';
SET time_zone='+9:00';
```



-----

#### 1번

* SstTest



#### 2번 : 스프링 부트 프로젝트 (기본)

* 프로젝트명 : ttsTest
* 패키지명 : com.multi.ttsTest



* 클래스 추가 : TtsTest
  * main() 포함
  * Java API 코드 복사
  * id/secret 입력
  * STT() 메소드로 추가 - 코드 복사
  * jsonToString() 메소드 추가
  * sample2.mp3 저장

