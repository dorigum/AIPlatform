### 스프링 프로젝트 생성

* `Naver AI Platform`에서 `Application` 등록
* 인증키 받아서 `API`를 통해 결과 받아 `JSP` 뷰페이지로 출력

* 유명인 얼굴 인식



### 스프링 부트 프로젝트 생성

* 프로젝트명 : naverAI
* 패키지명 : com.multi.naverai
* Dependencies
  * JDBC API / Oracle Driver / MyBatis Framework
  * Spring Web



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

  * `"faces"
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