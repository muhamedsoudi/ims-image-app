package com.ims.image.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ims.image.app.dto.ImageDTO;
import com.ims.image.app.service.ImageService;
import com.ims.image.app.util.constant.ImageStatusEnum;
  
@RestController
@RequestMapping(value = "api/image")
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public ResponseEntity<ImageDTO> uploadImage(@RequestParam(name = "imageDescription", required = false) String imageDescription ,
												@RequestParam(name = "imageCategory", required = true) String imageCategory ,
												@RequestPart MultipartFile imageMultipart) throws Exception {
		ImageDTO createdImage = imageService.uploadImage(imageDescription, imageCategory, imageMultipart);
		return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/reject" )
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> rejectImage(@RequestParam(name = "imageNo", required = true) String imageNo) throws Exception {
		imageService.rejectImage(imageNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/accept" )
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> acceptImage(@RequestParam(name = "imageNo", required = true) String imageNo) throws Exception {
		imageService.acceptImage(imageNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/info/{imageNo}" )
	public ResponseEntity<Object> loadAllAcceptedImages(@PathVariable String imageNo) throws Exception {
		ImageDTO image = imageService.getImageMetadata(imageNo);
		return new ResponseEntity<>(image, HttpStatus.OK);
	}
	
	@GetMapping(path = "/home" )
	public ResponseEntity<Object> loadAllAcceptedImages(@PageableDefault Pageable pageable) throws Exception {
		Page<ImageDTO> acceptedImages= imageService.getImagesByStatus(ImageStatusEnum.ACCEPTED, pageable);
		return new ResponseEntity<>(acceptedImages, HttpStatus.OK);
	}
	
	@GetMapping(path = "/admin-home" )
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> loadAllUnprocessedImages(@PageableDefault Pageable pageable) throws Exception {
		Page<ImageDTO> acceptedImages= imageService.getImagesByStatus(ImageStatusEnum.UNPROCESSED, pageable);
		return new ResponseEntity<>(acceptedImages, HttpStatus.OK);
	}
	
	@GetMapping(value = "/download", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE})
	Resource downloadImage(@RequestParam(name = "imageNo", required = true) String imageNo) throws Exception {
	    byte[] imageBytes = imageService.downloadImage(imageNo);
		return new ByteArrayResource(imageBytes);
	}
}

