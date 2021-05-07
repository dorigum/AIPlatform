package com.multi.aiservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class CFRCelebrityService {
	// AI API 호출해서 결과 받아오는 함수
	// 지금은 단순히 콘솔 창에 출력
	public ArrayList<CelebrityVO> clovaFaceRecogCel(String filePathName) {
		StringBuffer reqStr = new StringBuffer();
        String clientId = "syf9iq5za8";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "Xa1xTD6YGKyX4mMzqVVaHlJ9XdMgQB6KrBihSGww";//애플리케이션 클라이언트 시크릿값";
        ArrayList<CelebrityVO> celList = new ArrayList<CelebrityVO>();
        
        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = filePathName; // 사진 파일 path + filename
            
            File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/celebrity"; // 유명인 얼굴 인식
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString()); // 콘솔 창에 출력 : JSON 형식의 문자열로 출력
                // jsonToVoList() 호출 : 문자열 전달하고 VO리스트 받음
                celList = jsonToVoList(response.toString());
                
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return celList;
	}
	
	// API 호출 결과 전달받은 JSON 형식의 데이터를 전달받아서
	// confidence와 value를 추출하고
	// CelebrityVO 리스트에 추가해서 반환
	public ArrayList<CelebrityVO> jsonToVoList(String jsonStr){
		ArrayList<CelebrityVO> celList = new ArrayList<CelebrityVO>();
		
		try {
			// JSON 형식의 문자열에서 JSON 오브젝트 faces를 추출해서 JSONArray에 저장
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject)jsonParser.parse(jsonStr);
			JSONArray celebrityArray = (JSONArray)jsonObj.get("faces");
			
			if(celebrityArray != null) {
				// JSONArray 각 요소에서 value와 confidence 추출해서
				// CelebrityVO에 담아 리스트에 추가
				for(int i=0; i<celebrityArray.size(); i++) {
					JSONObject tempObj = (JSONObject)celebrityArray.get(i);
					tempObj = (JSONObject)tempObj.get("celebrity");
					
					double confidence = (double)tempObj.get("confidence");
					String value = (String)tempObj.get("value");
					
					// VO에 저장하고 리스트에 추가
					CelebrityVO vo = new CelebrityVO();
					vo.setConfidence(confidence);
					vo.setValue(value);
					
					celList.add(vo);
				}
				
				
			} else {
				// 유명인을 못 찾은 경우 ("faces: [ ])
				CelebrityVO vo = new CelebrityVO();
				vo.setConfidence(0);
				vo.setValue("없음");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return celList;
	}
}
