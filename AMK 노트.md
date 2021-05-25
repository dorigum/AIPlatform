# AI Makers Kit

* KT의 기가지니를 이용한 인공지능 스피커 개발 키트
* AMK와 라즈베리파이라는 컴퓨터를 이용하면 직접 인공지능 스피커를 만들 수 있음



### 인공지능 스피커

* 스피커, 마이크, 컴퓨터를 하나로 결합해서
* 인공지능 기능을 이용하여 사용자와 대화할 수 있는 스피커
* 상대방의 말을 알아 듣고, 질문에 대답하고, 행동할 수 있는 기능 제공
* 텍스트를 분석해서 음성으로 변환하거나, 음성을 텍스트로 변환 기능
* 사물 인터넷(IoT)와 연결되서 여러 센서 또는 장치 등을 제어하는 기능



### 음성 인식 스마트 스피커 제품

* KT 기가지니
* 아마존의 에코
* 구글의 구글 폼
* 네이버의 클로바 (프렌즈, 데스크)
* 카카오의 카카오미니 등
* 다양한 브랜드와 플랫폼에서 인공지능 스피커 제공



### AI Makers Kit 활용

(1) 조립

(2) 내 컴퓨터의 VNC Viewer 설치

(3) OS 이미지 레코딩 - SD카드

(4) 노트북에서 핫스팟 설정

* 라즈베리파이 IP 확인

(5) VNC Viewer 사용해서 원격 제어

	* 접속
	* id : pi
	* pass : raspberry

(6) AMK 따라하기



---

### (2) 내 컴퓨터의 VNC Viewer 설치

https://www.realvnc.com/en/connect/download/viewer/

* 설치 : English 선택하고 나머지 디폴트로 설치
* C:\Program Files\RealVNC\VNC Viewer 에 설치됨



---

### (3) OS 이미지 레코딩 - SD카드

* OS 이미지 다운로드 (p34~35)
* KT API Link 홈페이지에서 다운로드



* OS 이미지 레코딩 (p36~37)
* 이미지 레코딩 프로그램 Echer 설치
* SD카드에 이미지 레코딩



---

### (4) 노트북에서 핫스팟 설정

* 라즈베리파이 IP 확인
* 모바일 핫스팟 켜고 [설정] 으로 이동
  * 네트워크 이름 / 네트워크 암호 복사해놓음
* 핫스팟 연결을 위한 파일 2개 생성 (설명서에 없음)
  * ssh (확장자 없고, 내용 없음)
  * wpa_supplicant.conf
    * 이 파일 안에 네트워크 이름 / 네트워크 암호 지정
  * 2개 파일 SD카드에 복사



* 내 컴퓨터에 SD 카드 꽂고
* 2개 파일 SD카드에 복사



* SD 카드를 라즈베리파이에 꽂고
* 전원 연결



* 모바일 핫스팟 설정해서
* 라즈베리파이 IP 주소 출력



* 핫스팟에서 라즈베리파이 인식 못할 경우

(1) wpa_supplicant.conf 파일 열고

* 네트워크 이름 / 암호 확인

(2) SD 카드 꽂고 파일 다시 저장



----

### (5) VNC Viewer 사용해서 원격 제어

* VNC Viewer 실행 : 라즈베리파이 IP 입력
* 접속
 * id : pi
 * pass : raspberry



(1) 와이파이 설정

* 메뉴 / Preferences / Rasberry Pi configuration
* Localisation / Set WiFi Country / US 선택 / OK
* 오른쪽 상단 WiFi 아이콘에서 사용하고 있는 WiFi 선택하고 비밀번호 입력
* Check WiFi 실행 - 와이파이 연결 확인



(2) 오디오 및 마이크 테스트 (51~53)

* Check Audio 실행 : Enter / y
* Enter 눌러 녹음 시작 / y
* Enter 눌러 종료



(3) 클라이언트 키 다운로드 (54~56)

* KT에서 제공하는 음성인식, 음성합성 기술을 사용하기 위해서
* KT 기가지니 서버에서 접속해야 함
* API Key 발급 프로그램을 실행시켜서 API 키 발급
* 발급받은 키 확인
  * 파일 관리자에서 Downloads 폴더로 이동
  * clienKey.json
  * 우클릭해서 Text Editor 열어서
    * ClientSecret, clientKey, ClientId 확인



-----

### 클라이언트 키 입력

* [파일 매니저] 에서 home/pi/ai-makers-kit/python3 폴더로 이동
* user_auth.py 우클릭 Text Editor로 열어서
* Downloads 폴더에 있는 clientKey.json 파일에 있는
  * key, id, secret 복사해서 붙여넣기



---



ai-makers-kit / python3 폴더에 예제가 ex1 ~ ex9 들어 있음

터미널에서 작업
ai-makers-kit / python3 폴더까지 이동
: cd ai-makers-kit
: cd python3 
: 한 번에 이동 : cd ai-makers-kit/python3 
예제 파일 실행 : python3 ex1.....py



----

예제1 : 대기하고 있는 AMK를 호출하는 예제
-'호출어' : 대기하고 있는 인공지능 스피커를 호출하는 명려어
-AMK에서 제공하는 호출어 : '기가지니', '지니야', '친구야', '자기야'
- 4개중에서 선택해서 사용 (다른 단어로 변경하면 인식 못함)

실행 : python3 ex1_kwstest.py
결과 : 
- '기가지니' 호출
- "띠리링" 소리가 스피커에서 출력되면 AMK가 호출어를 제대로 인식한 것
- 호출어 변경
- Text Editor로 열고 '자기야"로 변경





----

### 음성인식(Speech-to-Text : STT) 기술

* 컴퓨터가 마이크와 같은 소리 센서를 통해 얻은 음성 신호를
* 단어나 문장으로 변환시키는 기술



### 예제2

* 음성 인식이 가능한 글자 수 : 100글자 이하
* python3 ex2_getVoice2Text.py
* 실행 후 스피커에 대고 말하기 : "안녕하세요"
  * 음성이 문자로 변환되어 출력
  * 인식 결과 : 안녕하세요



----

```python
dif genn--():
	for content in gggg:
		cccc
		yield message
		rms = xx.
```

#### yield

* return과 비슷하게 사용되는 키워드
* 함수가 제너레이터를 반환
* yield 호출되면 함수는 그 시점에서 일시정지
* 그 시점에 함수 안에 선언되어 있던 변수들 기억
* next() 를 통해서 다시 실행되면 yield 바로 다음 라인부터 실행을 이어나감



#### generator

* 메모리를 효율적으로 사용하면서 반복을 수행하도록 돕는 객체
* 제너레이터는 모든 값을 메모리에 담고 있지 않고, 그때 그때 값을 생성해서 반환

```python
def yield_func():
	for i in [1, 2, 3]:
		yield i
		print("리스트 요소 출력")
return = yield_func()
print(result)  # <generator object...>
print(next(result))  # 1
print(next(result))  # 리스트 요소 출력 / 2
print(next(result))  # 리스트 요소 출력 / 3
```



-----

### 예제3

* 지정한 텍스트를 음성으로 재생 (URL 반환)
  * 지정된 텍스트를 음성으로 바꾸어 음성을 들을 수 있는 인터넷 주소(URL)을 제공하는 예제
  * 사전에 입력된 텍스트는 음성 합성(TTS) 기술을 사용해서 음성으로 변환
* python3 ex3_getText2VoiceUrl.py 실행하고
* copy URL
* 웹 브라우저에서 Paste and go
  * 사운드 출력 컨트롤러는 출력되지만 플레이는 안 됨



----

### 예제4

* 지정한 텍스트를 음성으로 재생 (wav 생성)
  * 지정된 텍스트를 음성으로 바꾸어 wav 형태의 합성된 음성 파일을 제공하는 예제



(1) ex4_getText2VoiceStream.py 열고 출력할 텍스트 확인

* 실행 : python3 ex4_getText2VoiceStream.py
  * 출력되는 음성이 지정된 텍스트와 동일한지 확인
  * 생성된 testtts.wav 파일 확인
  * testtts.wav 파일 재생 : aplay testtts.wav



(2) 텍스트 변경 / wav 파일명 변경 

* 생성된 wav 파일 재생



### 사용자의 인증 API 키를 받아서 GRPC 패키지 사용

* GRPC : 라즈베리파이가 서버와 통신하기 위해 사용하는 프로토콜
* channel = grpc.secure_channel('{}:{}'.format(HOST, PORT), UA.getCredentials())
* stub = gigagenieRPC_pb2_grpc.GigagenieStub(channel)

```
	message = gigagenieRPC_pb2.reqText()
	message.lang=0
	message.mode=0
	message.text=inText
```



* 사용할 언어를 설정 (한국어인 경우 lang = 0)
* message.lang = 0



---

### 예제5

* 지정한 텍스트의 대한 대답을 텍스트로 받기
  * 미리 저장해둔 질문에 대한 대답을 텍스트로 받는 예제
  * KT API 서버자가 사용자의 질문을 분석하고, 알맞은 결과를 AMK로 전달
* 실행 : python3 ex5_queryText.py
* 현재 질의한 내용 : 안녕
* 질의에 대한 답변 : 안녕하세요. 너무 반가워요.



* 텍스트, userSession, deviceId
* 텍스트 : 답변을 받기 위한 질문
* userSession : 질의 문맥을 유지할 때 필요한 값으로 문맥에 따라서 다르게 설정
* deviceID : 해당 AI 스피커의 정보, 디바이스에 따라 다르게 설정. 일반적으로 MAC 주소로 설정



* MAC 주소 확인 명령어 : `ifconfig`

	message.queryText = text
	message.userSession = "1234"
	message.deviceId = "yourdevice"



----

### 예제6

* 음성으로 질문하고 텍스트로 대답 받기

  * 음성음식 기술과 음성합성 기술을 사용하는 예제
  * 사용자의 질문(음성)에 대한 답변을 텍스트로 받음

  

* 실행 : python3 ex6_queryVoice.py

* 결과 확인

  * 마이크에 대고 원하는 질문을 하면 대답 출력
    * 안녕하세요 / 반가워요
    * 지금 몇 시야? -> 지금 바로 이 순간을 즐기세요
    * 누구야? / 아이돌 바로~~~



----

### 예제7

* 호출어로 AMK를 호출하고, 인식한 음성을 텍스트로 출력
  * 호출어와 음성인식 결합 예제
    * ex1과 ex2 import 해서 사용
    * ex1 : 호출어 detect()
    * ex2 : 음성을 텍스트로 변환 getVoice2Text()



* python ex7_kwsstt.py
  * 호출  : '기가지니'
  * 음성 입력 : 안녕, ...
  * 텍스트로 변환된 결과 확인
  * 계속 호출, 음성 입력 진행 -> 실행 종료 (`Ctrl + \`)



----

### 예제3

* 호출어로 AMK 호출하고, AMK와 대화를 하는 예제
  * 호출어와 대화 결합 예제
    * ex1, ex4, ex6 import 해서 사용
    * ex1 : 호출어 test()
    * ex4 : getText2VoiceStream()
    * ex6 : queryByVoice()



* 실행 : python3 ex8_kwssttdss.py
  * 호출어 : '기가지니'
  * 띠리링 소리 후 질문 : 몇 시야 / 넌 누구야 / 넌 뭐하고 있니 ...
  * 날씨 인식 못함????
  * 음성으로 답변



----

### 예제9

* 버튼을 눌러서 대화하기
  * 호출어를 통해서 AMK를 호출하는 방법이 아닌
  * AMK에 부착된 아케이드 스위치를 눌러 호출하고
  * AMK와 대화하는 예제



* 실행 : python3 ex9_btnSttDss.py
  * 버튼 누름
  * 띠리링 1초 후 질문
  * AMK가 질문에 대해서 음성으로 답변