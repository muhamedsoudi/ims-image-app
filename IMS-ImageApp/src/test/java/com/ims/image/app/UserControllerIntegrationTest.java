package com.ims.image.app;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ims.image.app.controller.UserController;
import com.ims.image.app.model.User;
import com.ims.image.app.repository.UserRepository;
import com.ims.image.app.service.UserDetailsServiceImp;
import com.ims.image.app.service.UserService;
import com.ims.image.app.util.constant.UserTypeEnum;

@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@MockBean
	UserRepository userRepo;
	@MockBean
	UserService userService;
	@MockBean
	UserDetailsServiceImp userDetailsServiceImp;

	User user = new User(500l, "testUser", "test.user@ims.com", "Test User", "testPass", UserTypeEnum.USER);
	List<User> users = new ArrayList<>();

	@Test
	public void registerNewUser_success() throws Exception {

		Mockito.when(userRepo.save(user)).thenReturn(user);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/user/register")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(user));

		mockMvc.perform(mockRequest).andExpect(status().isCreated());
	}

	@Test
	public void getAllUsers_failed() throws Exception {

		users.add(user);
		Mockito.when(userRepo.findAll()).thenReturn(users);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin123", roles = "ADMIN")
	public void getAllUsers_success() throws Exception {

		users.add(user);
		Mockito.when(userRepo.findAll()).thenReturn(users);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
