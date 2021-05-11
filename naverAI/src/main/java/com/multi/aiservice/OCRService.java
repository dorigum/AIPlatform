package com.multi.aiservice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class OCRService {
	public String clovaOCRService(String filePathName) {

		String result = "";

		String apiURL = "https://c0beeab119e6453ba48ed2e20e458d72.apigw.ntruss.com/custom/v1/8642/1d5deb675dec7f624cbd23a006ab64cbc216cba69553bed124bd7797d208b733/general";
		String secretKey = "b0xmdkRMT0ROTXVEcU5jZ3pkd0NYbmZPdFFpUXJDb28=";
		String imageFile = filePathName;

		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setReadTimeout(30000);
			con.setRequestMethod("POST");
			String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			con.setRequestProperty("X-OCR-SECRET", secretKey);

			JSONObject json = new JSONObject();
			json.put("version", "V2");
			json.put("requestId", UUID.randomUUID().toString());
			json.put("timestamp", System.currentTimeMillis());
			JSONObject image = new JSONObject();
			image.put("format", "jpg");
			image.put("name", "demo");
			JSONArray images = new JSONArray();
			images.put(image);
			json.put("images", images);
			String postParams = json.toString();

			con.connect();
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			long start = System.currentTimeMillis();
			File file = new File(imageFile);
			writeMultiPart(wr, postParams, file, boundary);
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

			System.out.println(response); // JSON 형식의 문자열 출력
			System.out.println(response.toString());

			result = jsonToString(response.toString()); // 결과 반환
			System.out.println(result);

		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("--").append(boundary).append("\r\n");
		sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
		sb.append(jsonMessage);
		sb.append("\r\n");

		out.write(sb.toString().getBytes("UTF-8"));
		out.flush();

		if (file != null && file.isFile()) {
			out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
			StringBuilder fileString = new StringBuilder();
			fileString.append("Content-Disposition:form-data; name=\"file\"; filename=");
			fileString.append("\"" + file.getName() + "\"\r\n");
			fileString.append("Content-Type: application/octet-stream\r\n\r\n");
			out.write(fileString.toString().getBytes("UTF-8"));
			out.flush();

			try (FileInputStream fis = new FileInputStream(file)) {
				byte[] buffer = new byte[8192];
				int count;
				while ((count = fis.read(buffer)) != -1) {
					out.write(buffer, 0, count);
				}
				out.write("\r\n".getBytes());
			}

			out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
		}
		out.flush();
	}

	// 이미지에서 텍스트 추출
	// JSON 형식의 문자열에서 텍스트만 추출해서 문자열 반환 : inferText 값 추출
	public String jsonToString(String jsonStr) {
		String resultText = "";

		// 추출할 오브젝트 : images, fields, inferText의 값
		JSONObject jsonObj = new JSONObject(jsonStr);

		// jsonObj에서 images 추출 : 리스트
		JSONArray imgArray = (JSONArray) jsonObj.get("images");

		// 리스트의 요소가 1개밖에 없으므로 index를 0으로 지정
		JSONObject imgObj = (JSONObject) imgArray.get(0);

		// fields 추출 : 리스트
		JSONArray fieldsArray = (JSONArray) imgObj.get("fields");

		if (fieldsArray != null) {
			for (int i = 0; i < fieldsArray.length(); i++) { // size()가 아니고 length() (org.json.JSONArray 사용)
				JSONObject tempObj = (JSONObject) fieldsArray.get(i);
				String str = (String) tempObj.get("inferText");
				resultText += str + " ";
			}
		} else {
			System.out.println("없음");
		}
		return resultText;
	}
}