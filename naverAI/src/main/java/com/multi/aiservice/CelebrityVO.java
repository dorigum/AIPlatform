package com.multi.aiservice;

public class CelebrityVO {
	private String value; // 유명인 이름
	private double confidence; // 정확도 (유사 확신 정도)
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	
}
