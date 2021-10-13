package com.ims.image.app.service;

import org.springframework.stereotype.Service;

import com.ims.image.app.model.ImageBinary;

@Service
public interface ImageBinaryService {

	long save(ImageBinary imageBinary);
}
