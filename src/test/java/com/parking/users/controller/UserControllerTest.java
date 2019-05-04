package com.parking.users.controller;

import static com.parking.users.builders.UserBuilder.aUser;
import static com.parking.users.builders.UserValidationRequestBuilder.aUserValidationRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.users.UsersApplicationTests;
import com.parking.users.jsonapi.JsonApiBody;
import com.parking.users.jsonapi.JsonApiData;
import com.parking.users.model.User;
import com.parking.users.model.UserValidationRequest;
import com.parking.users.service.IUserService;
import com.parking.users.util.YamlProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UsersApplicationTests.class, initializers = ConfigFileApplicationContextInitializer.class)
public class UserControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private UserController userControllerMock;

	@Mock
	private IUserService userServiceMock;

	@Mock
	private YamlProperties yamlPropertiesMock;

	private ObjectMapper mapper;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(userControllerMock).build();
		Whitebox.setInternalState(userControllerMock, "userService", userServiceMock);
		this.mapper = new ObjectMapper();
		this.yamlPropertiesMock.appPathUserValidation = "/user/checkInuserValidation";
	}

	@Test
	public void userValidations_all_validations_are_success_test() throws Exception {
		// Arrange
		UserValidationRequest userValidationRequest = aUserValidationRequest().build();
		JsonApiBody<UserValidationRequest> jsonApiRequest = new JsonApiBody<>();
		JsonApiData<UserValidationRequest> data = new JsonApiData<>();
		data.setAttributes(userValidationRequest);
		jsonApiRequest.setData(data);

		User user = aUser().build();

		// Act
		when(this.userServiceMock.userValidations(any(), anyDouble())).thenReturn(user);
		ResultActions result = mockMvc.perform(post(this.yamlPropertiesMock.appPathUserValidation)
				.accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(this.mapper.writeValueAsString(jsonApiRequest)));
		// Assert
		result.andExpect(status().isOk());
	}

	@Test
	public void userValidations_user_exist() throws Exception {
		// Arrange
		UserValidationRequest userValidationRequest = aUserValidationRequest().build();
		JsonApiBody<UserValidationRequest> jsonApiRequest = new JsonApiBody<>();
		JsonApiData<UserValidationRequest> data = new JsonApiData<>();
		data.setAttributes(userValidationRequest);
		jsonApiRequest.setData(data);

		User user = aUser().build();
		// Act
		when(userServiceMock.userValidations(any(), anyDouble())).thenReturn(user);
		ResultActions result = mockMvc.perform(post(this.yamlPropertiesMock.appPathUserValidation)
				.accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(this.mapper.writeValueAsString(jsonApiRequest)));
		// Assert
		result.andExpect(status().isOk()).andExpect(jsonPath("$.data").exists())
				.andExpect(jsonPath("$.data.attributes.userName").value(user.getUserName()));
	}

	@Test(expected = Exception.class)
	public void userValidations_throws_exception_test() throws Exception {
		// Arrange
		Double totalValueToPay = 0d;
		UserValidationRequest userValidationRequest = aUserValidationRequest().build();
		JsonApiBody<UserValidationRequest> jsonApiRequest = new JsonApiBody<>();
		jsonApiRequest.newDataInstance().setAttributes(userValidationRequest);

		// Act
		when(this.userServiceMock.userValidations(any(), totalValueToPay)).thenReturn(false);
		mockMvc.perform(post(this.yamlPropertiesMock.appPathUserValidation).accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(this.mapper.writeValueAsString(jsonApiRequest)));
	}

	@Test
	public void userValidations_path_does_not_exists_test() throws Exception {
		// Arrange
		String cardNumber = "008890";
		// Act
		ResultActions result = mockMvc.perform(get("/user/getUserByCardNumbers").param("cardNumber", cardNumber));
		// Assert
		result.andExpect(status().is4xxClientError());
	}
}
