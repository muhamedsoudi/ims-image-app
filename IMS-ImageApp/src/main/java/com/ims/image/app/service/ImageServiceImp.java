package com.ims.image.app.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ims.image.app.dto.ImageDTO;
import com.ims.image.app.exception.InvalidArgumentException;
import com.ims.image.app.exception.NotFoundException;
import com.ims.image.app.exception.OperationNotSupportedException;
import com.ims.image.app.model.Image;
import com.ims.image.app.model.ImageBinary;
import com.ims.image.app.repository.ImageBinaryRepository;
import com.ims.image.app.repository.ImageRepository;
import com.ims.image.app.util.Util;
import com.ims.image.app.util.constant.ImageCategoryEnum;
import com.ims.image.app.util.constant.ImageFormatEnum;
import com.ims.image.app.util.constant.ImageStatusEnum;

@Service
@Transactional
public class ImageServiceImp implements ImageService {

	@Autowired
	ImageRepository imageRepo;
	@Autowired
	ImageBinaryRepository imageBinaryRepo;
	@Autowired
	Util util;
	private ModelMapper modelMapper = new ModelMapper(); 
	
	@Override
	public ImageDTO uploadImage(String imageDescription, String ImageCategory, MultipartFile imageMultipart) throws InvalidArgumentException, IOException{
		
		if (!ImageFormatEnum.isEndsWith(imageMultipart.getContentType()))
			throw new InvalidArgumentException(String.format("Received an Invalid ImageFormat, ImageFormat must be either jpg, png or gif."));
		
		if (!ImageCategoryEnum.isValid(ImageCategory))
			throw new InvalidArgumentException(String.format("Received an Invalid ImageCategory:[%s], ImageCategory must be either living thing, machine or nature.",ImageCategory));
		
		Image newImage= new Image();
		// Set createdBy to the current logged-in user
		newImage.setCreatedBy(util.getLoggedInUser());
		// Set default status to UNPROCESSED
		newImage.setImageStatus(ImageStatusEnum.UNPROCESSED);
		newImage.setImageFormat(ImageFormatEnum.toImageFormatEnum(imageMultipart.getContentType()));
		newImage.setImageCategory(ImageCategoryEnum.toImageCategoryEnum(ImageCategory));
		newImage.setImageName(imageMultipart.getOriginalFilename());
		newImage.setImageDescription(imageDescription);
		// Get Image Dimension
		getImageDimension(newImage, imageMultipart);
		ImageBinary newImageBinary = new ImageBinary(imageMultipart.getBytes());
		newImage.setImageBinary(newImageBinary);
		return modelMapper.map(imageRepo.save(newImage), ImageDTO.class);
	}

	@Override
	public boolean rejectImage(String imageNo) throws NotFoundException{
		Image image= imageRepo.findByImageNo(imageNo)
				.orElseThrow(() -> new NotFoundException("Couldn't find image with the given number"));
		if (image.getImageStatus().compareTo(ImageStatusEnum.REJECTED) != 0)
			return true;
		long imageBinaryId = image.getImageBinary().getImageBinaryId();
		// Set Binary Content To Null
		image.setImageBinary(null);
		image.setImageStatus(ImageStatusEnum.REJECTED);
		imageRepo.save(image);
		imageBinaryRepo.deleteById(imageBinaryId);
		return true;
	}
	
	@Override
	public boolean acceptImage(String imageNo) throws OperationNotSupportedException, NotFoundException{
		Image image= imageRepo.findByImageNo(imageNo)
				.orElseThrow(() -> new NotFoundException("Couldn't find image with the given number"));
		// Check the current status of the selected image
		if (image.getImageStatus().compareTo(ImageStatusEnum.UNPROCESSED) != 0)
			throw new OperationNotSupportedException("To Accept and image, The current image status must be UNPROCESSED.");
		
		image.setImageStatus(ImageStatusEnum.ACCEPTED);
		imageRepo.save(image);
		return true;
	}

	@Override
	public Page<ImageDTO> getImagesByStatus(ImageStatusEnum imageStatus, Pageable pageable) {
		List<ImageDTO> imageDtoList = new ArrayList<>();
		Page<Image> imageList = imageRepo.findByImageStatus(imageStatus, pageable);
		for(Image image: imageList.getContent()) {
			imageDtoList.add(modelMapper.map(image, ImageDTO.class));
		}
		return new PageImpl<ImageDTO>(imageDtoList);
	}
	

	@Override
	public ImageDTO getImageMetadata(String imageNo) {
		Image image= imageRepo.findByImageNo(imageNo)
				    .orElseThrow(() -> new NotFoundException("Couldn't find image with the given number"));
		return modelMapper.map(image, ImageDTO.class);
	}

	@Override
	public byte[] downloadImage(String imageNo) {
		byte[] imageBytes= imageRepo.findByImageNo(imageNo)
			        .orElseThrow(() -> new NotFoundException("Couldn't find image with the given number"))
			        .getImageBinary()
			        .getImageBinaryContent();
		return imageBytes;
	}
	
	private Image getImageDimension(Image image, MultipartFile imageMultipart) throws IOException {
		BufferedImage  bufferImage=ImageIO.read(imageMultipart.getInputStream());
		int width   = bufferImage.getWidth();
		int height  = bufferImage.getHeight();
		return image.setImageDimension(String.format("%d*%d", width, height));
	}

}
