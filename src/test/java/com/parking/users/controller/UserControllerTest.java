package com.parking.users.controller;

import static com.parking.users.builders.UserBuilder.aUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.JsonObject;
import com.parking.users.jsonapi.JsonApiData;
import com.parking.users.jsonapi.JsonApiBody;
import com.parking.users.model.User;
import com.parking.users.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private UserController userControllerMock;

	@Mock
	private UserService userServiceMock;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(userControllerMock).build();
		Whitebox.setInternalState(userControllerMock, "userService", userServiceMock);
	}

	@Test
	public void userValidations_test() {
		// Arrange
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("cardNumber", "008890");
		JsonApiBody<JsonObject> jsonApiRequest = new JsonApiBody<>();
		JsonApiData<JsonObject> data = new JsonApiData<>();
		data.setAttributes(jsonObject);
		jsonApiRequest.setData(data);
		// Act
		// Assert
	}
}
