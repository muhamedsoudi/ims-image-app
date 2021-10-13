package com.ims.image.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ims.image.app.util.constant.UserTypeEnum;

public class UserDTO {

	@NotNull
	@NotEmpty
	private String username;
	@NotNull
	@NotEmpty
	@Email
	private String userEmail;
	@NotEmpty
	private String userFullName;
	@NotNull
	@NotEmpty
	@JsonProperty(access = Access.WRITE_ONLY)
	private String userPassword;
	@NotNull
	private UserTypeEnum userType;
	
	public UserDTO() {}

	public UserDTO(String username, String userFullName, String userPassword, UserTypeEnum userType) {
		super();
		this.username = username;
		this.userFullName = userFullName;
		this.userPassword = userPassword;
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public UserDTO setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
