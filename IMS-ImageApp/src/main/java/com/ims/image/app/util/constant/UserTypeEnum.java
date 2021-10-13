package com.ims.image.app.util.constant;

public enum UserTypeEnum {

	ADMIN("ADMIN"), USER("USER");

	private String userType;

	private UserTypeEnum(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public static boolean isValid(String userType) {
		for (UserTypeEnum userTypeEnum : values())
			if (userTypeEnum.userType.toLowerCase().equals(userType.toLowerCase()))
				return true;
		return false;
	}

}