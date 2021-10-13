package com.ims.image.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.image.app.model.ImageBinary;

@Repository
public interface ImageBinaryRepository extends JpaRepository<ImageBinary, Long> {

}
