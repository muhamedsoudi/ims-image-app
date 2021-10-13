package com.ims.image.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ims.image.app.model.User;
import com.ims.image.app.service.UserService;

@Component
public class Util {

	@Autowired
	UserService userService;

	public User getLoggedInUser() {

		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return userService.getUserByUsername(username);
	}
}
