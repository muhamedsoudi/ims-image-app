package com.ims.image.app.util.constant;

public enum ImageFormatEnum {
	
	JPG("image/jpg"),
	PNG("image/png"),
	GIF("image/gif"),
	OTHERS("others");


	private final String imageFormat;

	private ImageFormatEnum(String imageFormat) {
		this.imageFormat = imageFormat;
	}

	public String getImageFormat() {
		return imageFormat;
	}

	
	public static boolean isValid(String imageFormat) {
		for (ImageFormatEnum imageFormatEnum : values())
			if (imageFormatEnum.imageFormat.equals(imageFormat.toLowerCase()))
				return true;
		return false;
	}
	
	public static boolean isEndsWith(String imageFormat) {
		for (ImageFormatEnum imageFormatEnum : values())
			if (imageFormat.toLowerCase().endsWith(imageFormatEnum.imageFormat))
				return true;
		return false;
	}
	
	public static ImageFormatEnum toImageFormatEnum(String imageFormat) {
		for (ImageFormatEnum imageFormatEnum : values())
			if (imageFormatEnum.imageFormat.equals(imageFormat.toLowerCase()))
				return imageFormatEnum;
		return ImageFormatEnum.OTHERS;
	}

}
