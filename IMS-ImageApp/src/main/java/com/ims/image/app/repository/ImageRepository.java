package com.ims.image.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.image.app.model.Image;
import com.ims.image.app.util.constant.ImageStatusEnum;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	Page<Image> findByImageStatus(ImageStatusEnum imageStatus, Pageable pageable);
	
	List<Image> findByImageStatus(ImageStatusEnum imageStatus);
	
	Optional<Image> findByImageNo (String imageNo);
	
}
