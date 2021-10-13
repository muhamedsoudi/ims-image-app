package com.ims.image.app;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ims.image.app.controller.ImageController;
import com.ims.image.app.dto.ImageDTO;
import com.ims.image.app.model.Image;
import com.ims.image.app.model.User;
import com.ims.image.app.repository.ImageRepository;
import com.ims.image.app.service.ImageService;
import com.ims.image.app.service.UserDetailsServiceImp;
import com.ims.image.app.util.constant.ImageCategoryEnum;
import com.ims.image.app.util.constant.ImageFormatEnum;
import com.ims.image.app.util.constant.ImageStatusEnum;
import com.ims.image.app.util.constant.UserTypeEnum;

@WebMvcTest(ImageController.class)
public class ImageControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@MockBean
	ImageRepository imageRepo;
	@MockBean
	ImageService imageService;
	@MockBean
	UserDetailsServiceImp userDetailsServiceImp;
	private ModelMapper modelMapper = new ModelMapper();
	
	private String imageNo = "296e0855-c8r1-22ff-a3fc-bf5b5541290d";
	User user = new User(500l, "testUser", "test.user@ims.com", "Test User", "testPass", UserTypeEnum.USER);
	Image image = new Image(10001l, imageNo, "testImage", "Test Desc", "10*10",ImageStatusEnum.ACCEPTED, null, user, ImageCategoryEnum.NATURE,ImageFormatEnum.GIF);
	ImageDTO imageDTO = modelMapper.map(image, ImageDTO.class);
	List<Image> images = new ArrayList<>();
	@Test
	public void getImageInfo_success() throws Exception {

	    Mockito.when(imageService.getImageMetadata(imageNo)).thenReturn(imageDTO);
	    mockMvc.perform(MockMvcRequestBuilders
	            .get(String.format("/api/image/info/%s",imageNo))
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.imageNo", is(imageNo)));
	}
	
	
	@Test
	public void getAllUnProcessedImages_failed() throws Exception {

		images.add(image);
		Mockito.when(imageRepo.findAll()).thenReturn(images);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/image/admin-home").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin123", roles = "ADMIN")
	public void getAllUnProcessedImages_success() throws Exception {

		images.add(image);
		Mockito.when(imageRepo.findAll()).thenReturn(images);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/image/admin-home").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
