package com.ims.image.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.image.app.dto.UserDTO;
import com.ims.image.app.exception.InvalidArgumentException;
import com.ims.image.app.exception.NotFoundException;
import com.ims.image.app.model.User;
import com.ims.image.app.repository.UserRepository;
import com.ims.image.app.util.constant.UserTypeEnum;

@Service
@Transactional
public class UserServiceImp implements UserService{

	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public UserDTO registerNewUser(UserDTO userDto) throws InvalidArgumentException {

		if (!UserTypeEnum.isValid(userDto.getUserType().toString()))
			throw new InvalidArgumentException(
					String.format("An Invalid UserType:[%s], UserType must be either USER or ADMIN.",
							userDto.getUserType().toString()));
		User user = modelMapper.map(userDto, User.class);
		user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		User savedUser = userRepo.save(user);
		return modelMapper.map(savedUser, UserDTO.class).setUsername(userDto.getUsername());
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("Couldn't find user with the given username"));
	}
	
	@Override
	public List<UserDTO> getAllUsers(){
		List<UserDTO> users = new ArrayList<>();
		for(User user: userRepo.findAll()) {
			users.add(modelMapper.map(user, UserDTO.class));
		}
		return users;
	}
}
