package com.ims.image.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ims.image.app.util.constant.UserTypeEnum;


@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "USER_ID", length = 100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	@Column(name = "USERNAME", updatable = false, nullable = false, unique = true)
	private String username;
	@Column(name = "USER_EMAIL", updatable = false, nullable = false, unique = true)
	private String userEmail;
	@Column(name = "USER_FULL_NAME", updatable = true, nullable = true, unique = false)
	private String userFullName;
	@Column(name = "USER_PASSWORD", updatable = true, nullable = false, unique = false)
	private String userPassword;
	@Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE", nullable=false)
	private UserTypeEnum userType;
	
	public User() {}
	
	
		
	public User(long userId, String username, String userEmail, String userFullName, String userPassword,
			UserTypeEnum userType) {
		super();
		this.userId = userId;
		this.username = username;
		this.userEmail = userEmail;
		this.userFullName = userFullName;
		this.userPassword = userPassword;
		this.userType = userType;
	}



	public long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
	
}