package com.multi.aiservice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AIRestController {
	@Autowired
	private OCRService ocrService;

	@Autowired
	private PoseEstimationService poseService;
	
	@Autowired
	private ObjectDetectionService objService;
	
	@Autowired
	private STTService sttService;
	
	@Autowired
	private TTSService ttsService;

	@RequestMapping("/clovaOCR")
	public String clovaOCR(@RequestParam("uploadFile") MultipartFile file) {
		String result = "";

		try {
			// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
			String uploadPath = "c:/ai/";

			// 2. 원본 파일 이름
			String originalFileName = file.getOriginalFilename();

			// 3. 파일 생성
			String filePathName = uploadPath + originalFileName;
			File file1 = new File(filePathName);

			// 4. 서버로 전송
			file.transferTo(file1);

			result = ocrService.clovaOCRService(filePathName);
			System.out.println(result);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	

	@RequestMapping("/poseDetect")
	public ArrayList<PoseVO> poseDetect(@RequestParam("uploadFile") MultipartFile file)
										throws IOException{
		
			//1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
			  String uploadPath =  "c:/ai/";
			  
			  //2.원본 파일 이름
			  String originalFileName = file.getOriginalFilename();  
			  
			  //3. 파일 생성 
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  
			  //4. 서버로 전송
			  file.transferTo(file1);			  
			  
			  ArrayList<PoseVO> poseList = poseService.poseEstimate(filePathName);
		
		return poseList;
	}
	
	@RequestMapping("/objectDetect")
	public ArrayList<ObjectVO> objectDetect(@RequestParam("uploadFile") MultipartFile file)
										throws IOException{
		
			//1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
			  String uploadPath =  "c:/ai/";
			  
			  //2.원본 파일 이름
			  String originalFileName = file.getOriginalFilename();  
			  
			  //3. 파일 생성 
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  
			  //4. 서버로 전송
			  file.transferTo(file1);			  
			  
			  ArrayList<ObjectVO> objectList = objService.objectDetect(filePathName);
		
		return objectList;
	}
	
	@RequestMapping("/clovaSTT")
	public String STT(@RequestParam("uploadFile") MultipartFile file,
					  @RequestParam("language") String language) {
		String result = "";

		try {
			// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
			String uploadPath = "c:/ai/";

			// 2. 원본 파일 이름
			String originalFileName = file.getOriginalFilename();

			// 3. 파일 생성
			String filePathName = uploadPath + originalFileName;
			File file1 = new File(filePathName);

			// 4. 서버로 전송
			file.transferTo(file1);

			result = sttService.clovaSpeechToText(filePathName, language);
			System.out.println(result);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping("/clovaTTS")
	public String TTS(@RequestParam("uploadFile") MultipartFile file,
					  @RequestParam("language") String language) {
		String result = "";

		try {
			// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
			String uploadPath = "c:/ai/";

			// 2. 원본 파일 이름
			String originalFileName = file.getOriginalFilename();

			// 3. 파일 생성
			String filePathName = uploadPath + originalFileName;
			File file1 = new File(filePathName);

			// 4. 서버로 전송
			file.transferTo(file1);

			result = ttsService.clovaTextToSpeech(filePathName, language);
			System.out.println(result);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result; // 음성 파일 이름 변환
	}
	
	@RequestMapping("/chatbotCall")
	public String chatbot(@RequestParam("message") String message) {
		String result = ChatbotService.main(message);
		return result;
	}
}