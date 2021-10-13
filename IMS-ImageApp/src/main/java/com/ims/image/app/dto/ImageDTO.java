package com.ims.image.app.dto;

import com.ims.image.app.util.constant.ImageCategoryEnum;
import com.ims.image.app.util.constant.ImageFormatEnum;
import com.ims.image.app.util.constant.ImageStatusEnum;

public class ImageDTO {
	
	
	private String imageNo;
	private String imageName;
	private String imageDescription;
	private String imageDimension;
	private ImageStatusEnum imageStatus;
	private long imageBinaryId;
	private String createdBy;
	private ImageCategoryEnum imageCategory;
	private ImageFormatEnum imageFormat;
	
	public ImageDTO() {}


	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	public ImageStatusEnum getImageStatus() {
		return imageStatus;
	}

	public void setImageStatus(ImageStatusEnum imageStatus) {
		this.imageStatus = imageStatus;
	}

	public long getImageBinaryId() {
		return imageBinaryId;
	}


	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public ImageCategoryEnum getImageCategory() {
		return imageCategory;
	}

	public void setImageCategory(ImageCategoryEnum imageCategory) {
		this.imageCategory = imageCategory;
	}

	public ImageFormatEnum getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(ImageFormatEnum imageFormat) {
		this.imageFormat = imageFormat;
	}

	public String getImageNo() {
		return imageNo;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public void setImageNo(String imageNo) {
		this.imageNo = imageNo;
	}


	public void setImageBinaryId(long imageBinaryId) {
		this.imageBinaryId = imageBinaryId;
	}


	public String getImageDimension() {
		return imageDimension;
	}


	public void setImageDimension(String imageDimension) {
		this.imageDimension = imageDimension;
	}

	
	
}
