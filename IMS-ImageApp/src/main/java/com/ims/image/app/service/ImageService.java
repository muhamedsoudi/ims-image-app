package com.ims.image.app.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ims.image.app.dto.ImageDTO;
import com.ims.image.app.exception.InvalidArgumentException;
import com.ims.image.app.exception.OperationNotSupportedException;
import com.ims.image.app.util.constant.ImageStatusEnum;

@Service
public interface ImageService {

	ImageDTO uploadImage(String imageDescription, String ImageCategory, MultipartFile image) throws InvalidArgumentException, IOException;
	boolean rejectImage(String imageNo);
	boolean acceptImage(String imageNo) throws OperationNotSupportedException;
	Page<ImageDTO> getImagesByStatus(ImageStatusEnum imageStatus, Pageable pageable);
	ImageDTO getImageMetadata(String imageNo);
	byte[] downloadImage(String imageNo);
}
