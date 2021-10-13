package com.ims.image.app.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ims.image.app.exception.NotFoundException;
import com.ims.image.app.model.User;
import com.ims.image.app.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userCredentials = userRepo.findByUsername(username)
							  .orElseThrow(() -> new NotFoundException("Couldn't find user with the given username"));
		return toUserDetails(userCredentials);
	}

	private UserDetails toUserDetails(User user) {
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getUserPassword())
				.roles(Arrays.asList(user.getUserType().getUserType()).toArray(String[]::new)).build();
	}
}
