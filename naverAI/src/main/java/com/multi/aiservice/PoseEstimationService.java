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
public class PoseEstimationService {
	public ArrayList<PoseVO> poseEstimate(String filePathName) {
		StringBuffer reqStr = new StringBuffer();
		String clientId = "syf9iq5za8";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "Xa1xTD6YGKyX4mMzqVVaHlJ9XdMgQB6KrBihSGww";// 애플리케이션 클라이언트 시크릿값";
		ArrayList<PoseVO> poseList = new ArrayList<PoseVO>();
		
		try {
			String paramName = "image"; // 파라미터명은 image로 지정
			String imgFile = filePathName;
			File uploadFile = new File(imgFile);
			String apiURL = "https://naveropenapi.apigw.ntruss.com/vision-pose/v1/estimate"; // 사람 인식
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
			writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"")
					.append(LINE_FEED);
			writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
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
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 오류 발생
				System.out.println("error!!!!!!! responseCode= " + responseCode);
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			String inputLine;
			if (br != null) {
				StringBuffer response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();
				System.out.println(response.toString()); // 결과 출력

				poseList = jsonToVOList(response.toString());

			} else {
				System.out.println("error !!!");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return poseList;
	}
	
	public ArrayList<PoseVO> jsonToVOList(String jsonStr){
		ArrayList<PoseVO> poseList = new ArrayList<PoseVO>();
		double x;
		double y;
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject)jsonParser.parse(jsonStr);
			JSONArray poseArray = (JSONArray)jsonObj.get("predictions");
			JSONObject predic0 = (JSONObject)poseArray.get(0);
		
		for(int i=0; i<18; i++) {
			if(predic0.get(i + "") != null) {
				JSONObject tempObj = (JSONObject) predic0.get(i + "");
				x = (double) tempObj.get("x");
				y = (double) tempObj.get("y");
			} else {
				x = 0;
				y = 0;
			}
			
			PoseVO vo = new PoseVO();
			vo.setIndex(i);
			vo.setX(x);
			vo.setY(y);
			
			poseList.add(vo);
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return poseList;
	}
}