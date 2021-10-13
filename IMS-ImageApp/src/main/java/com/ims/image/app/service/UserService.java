package com.ims.image.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ims.image.app.dto.UserDTO;
import com.ims.image.app.exception.InvalidArgumentException;
import com.ims.image.app.model.User;

@Service
public interface UserService {

	UserDTO registerNewUser(UserDTO userDto) throws InvalidArgumentException;
	
	User getUserByUsername(String username);

	List<UserDTO> getAllUsers();
}
