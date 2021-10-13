package com.ims.image.app.util.constant;

public enum ImageCategoryEnum {

	LIVING_THING("living thing"), MACHINE("machine"), NATURE("nature"), OTHERS("others"); // Used as a default value

	private String imageCategory;

	private ImageCategoryEnum(String imageCategory) {
		this.imageCategory = imageCategory;
	}

	public String getImageCategory() {
		return imageCategory;
	}

	public void setImageCategory(String imageCategory) {
		this.imageCategory = imageCategory;
	}

	public static boolean isValid(String imageCategory) {
		for (ImageCategoryEnum imageCategoryEnum : values())
			if (imageCategoryEnum.imageCategory.equals(imageCategory.toLowerCase()))
				return true;
		return false;
	}

	public static ImageCategoryEnum toImageCategoryEnum(String imageCategory) {

		for (ImageCategoryEnum imageCategoryEnum : values())
			if (imageCategoryEnum.imageCategory.equals(imageCategory.toLowerCase()))
				return imageCategoryEnum;
		return ImageCategoryEnum.OTHERS;

	}
}
