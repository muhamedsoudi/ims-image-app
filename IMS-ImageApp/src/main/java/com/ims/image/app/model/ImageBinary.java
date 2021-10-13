package com.ims.image.app.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE_BINARY")
public class ImageBinary implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long imageBinaryId;
	
	@Lob
	@Column(name = "IMAGE_BINARY_CONTENT",length=2048)
	@Basic(fetch=FetchType.LAZY)
	private byte[] imageBinaryContent;

	public ImageBinary() {}
	
	
	
	public ImageBinary(long imageBinaryId, byte[] imageBinaryContent) {
		super();
		this.imageBinaryId = imageBinaryId;
		this.imageBinaryContent = imageBinaryContent;
	}



	public ImageBinary(byte [] imageBinaryContent) {
		this.imageBinaryContent = imageBinaryContent;
	}

	public long getImageBinaryId() {
		return imageBinaryId;
	}

	public byte[] getImageBinaryContent() {
		return imageBinaryContent;
	}

	public void setImageBinaryContent(byte[] imageBinaryContent) {
		this.imageBinaryContent = imageBinaryContent;
	}
	
	
	
	
	
}