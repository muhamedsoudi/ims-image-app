package com.ims.image.app.util.constant;

public enum ImageStatusEnum {

	ACCEPTED("ACCEPTED"),
	REJECTED("REJECTED"),
	UNPROCESSED("UNPROCESSED");
	
	private String imageStatus;

	private ImageStatusEnum(String imageStatus) {
		this.imageStatus = imageStatus;
	}

	public String getImageStatus() {
		return imageStatus;
	}

	public void setImageStatus(String imageStatus) {
		this.imageStatus = imageStatus;
	}
	
	
}
