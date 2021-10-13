package com.ims.image.app.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ims.image.app.util.constant.ImageCategoryEnum;
import com.ims.image.app.util.constant.ImageFormatEnum;
import com.ims.image.app.util.constant.ImageStatusEnum;

@Entity
@Table(name = "IMAGE")
public class Image implements Serializable {

	private static final long serialVersionUID = 5018929484448831078L;

	@Id
	@Column(name = "IMG_ID", length = 100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long imageId;
	
	@Column(name = "IMG_NO", length = 100)
	private String imageNo;
	
	@Column(name = "IMG_NAME")
	private String imageName;
	
	@Column(name = "IMG_DESCRIPTION", length = 200)
	private String imageDescription;
	
	@Column(name = "IMG_DIMENSION")
	private String imageDimension;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "IMG_STATUS", nullable=false)
	private ImageStatusEnum imageStatus;
	    
	@OneToOne(fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "IMAGE_BINARY_ID")
	private ImageBinary imageBinary;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID",nullable=false)
	private User createdBy;

	@Enumerated(EnumType.STRING)
    @Column(name = "IMG_CATEGORY", nullable=false)
	private ImageCategoryEnum imageCategory;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "IMG_FORMAT", nullable=false)
	private ImageFormatEnum imageFormat;

	public Image() {
		this.imageNo = UUID.randomUUID().toString();
	}

	
	
	public Image(long imageId, String imageNo, String imageName, String imageDescription, String imageDimension,
			ImageStatusEnum imageStatus, ImageBinary imageBinary, User createdBy, ImageCategoryEnum imageCategory,
			ImageFormatEnum imageFormat) {
		super();
		this.imageId = imageId;
		this.imageNo = imageNo;
		this.imageName = imageName;
		this.imageDescription = imageDescription;
		this.imageDimension = imageDimension;
		this.imageStatus = imageStatus;
		this.imageBinary = imageBinary;
		this.createdBy = createdBy;
		this.imageCategory = imageCategory;
		this.imageFormat = imageFormat;
	}



	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	public ImageBinary getImageBinary() {
		return imageBinary;
	}

	public void setImageBinary(ImageBinary imageBinary) {
		this.imageBinary = imageBinary;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
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

	public long getImageId() {
		return imageId;
	}

	public ImageStatusEnum getImageStatus() {
		return imageStatus;
	}

	public void setImageStatus(ImageStatusEnum imageStatus) {
		this.imageStatus = imageStatus;
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

	public String getImageDimension() {
		return imageDimension;
	}

	public Image setImageDimension(String imageDimension) {
		this.imageDimension = imageDimension;
		return this;
	}
	
	
	
}