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
										throws IllegalStateException, IOException{
		
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
}