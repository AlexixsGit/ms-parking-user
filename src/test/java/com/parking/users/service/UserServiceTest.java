package com.parking.users.service;

import static com.parking.users.builders.CardBuilder.aCard;
import static com.parking.users.builders.ErrorDetailBuilder.anErrorDetail;
import static com.parking.users.builders.JsonApiErrorBuilder.aJsonApiError;
import static com.parking.users.builders.UserBuilder.aUser;
import static com.parking.users.builders.UserValidationRequestBuilder.aUserValidationRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.parking.users.exceptions.ApiException;
import com.parking.users.jsonapi.JsonApiBody;
import com.parking.users.jsonapi.JsonApiError;
import com.parking.users.model.User;
import com.parking.users.model.UserValidationRequest;
import com.parking.users.repository.IUserRepository;
import com.parking.users.util.YamlProperties;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private IUserRepository userRepositoryMock;

	@InjectMocks
	private UserService userServiceMock;

	@Mock
	private YamlProperties yamlPropertiesMock;

	@Before
	public void setup() {
		this.yamlPropertiesMock.appErrorsUserWithoutBalanceCode = "0004";
		this.yamlPropertiesMock.appErrorsUserWithoutBalanceMessage = "El usuario no tiene saldo suficiente";
		this.yamlPropertiesMock.appErrorsUserValidationRequiredTitle = "Datos obligatorios vacios";
		this.yamlPropertiesMock.appPathUserValidation = "/user/checkInuserValidation";
		this.yamlPropertiesMock.appErrorsUserNotFoundCode = "0003";
		this.yamlPropertiesMock.appErrorsCardNumberEmptyCode = "0001";
	}

	@Test
	public void checkIfUserByCardNumberExists_test() throws ApiException {
		// Arrange
		String cardNumber = "008893";
		User expected = aUser().withCard(aCard().withNumber(cardNumber).build()).build();

		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expected);
		boolean actual = this.userServiceMock.checkIfUserByCardNumberExists(cardNumber);
		// Assert
		assertTrue(actual);
	}

	@Test
	public void checkIfUserByCardNumberNotExists_test() throws ApiException {
		// Arrange
		String cardNumber = "008893";
		User expected = aUser().build();

		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expected);
		boolean actual = this.userServiceMock.checkIfUserByCardNumberExists(cardNumber);
		// Assert
		assertFalse(actual);
	}

	@Test(expected = ApiException.class)
	public void checkIfUserByCardNumber_throws_exception_test() throws ApiException {
		// Arrange
		String cardNumber = "008893";
		User expected = aUser().withCard(null).build();

		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expected);
		this.userServiceMock.checkIfUserByCardNumberExists(cardNumber);
	}

	@Test
	public void userValidations_user_exists_and_has_balance_test() throws ApiException {
		// Arrange
		String cardNumber = "008890";
		JsonApiBody<UserValidationRequest> jsonApiBody = new JsonApiBody<>();
		jsonApiBody.newDataInstance().setAttributes(aUserValidationRequest().build());
		User expectedUser = aUser().build();
		Double totalValueToPay = 100000d;
		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expectedUser);
		User actual = (User) this.userServiceMock.userValidations(jsonApiBody, totalValueToPay);
		// Assert
		assertEquals(expectedUser, actual);
	}

	@Test
	public void userValidations_user_exists_and_does_not_has_balance_test() throws ApiException {
		// Arrange
		String cardNumber = "008890";
		JsonApiBody<UserValidationRequest> jsonApiBody = new JsonApiBody<>();
		jsonApiBody.newDataInstance().setAttributes(aUserValidationRequest().build());
		User expectedUser = aUser().build();
		Double totalValueToPay = 300000d;

		JsonApiError expected = aJsonApiError()
				.with(anErrorDetail().withCode(this.yamlPropertiesMock.appErrorsUserWithoutBalanceCode)).build();

		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expectedUser);
		JsonApiError actual = (JsonApiError) this.userServiceMock.userValidations(jsonApiBody, totalValueToPay);
		// Assert
		assertNotNull(actual.getErrors());
		assertTrue(actual.getErrors().size() > 0);
		assertEquals(expected.getErrors().get(0).getCode(), actual.getErrors().get(0).getCode());
	}

	@Test
	public void userValidations_user_not_exists_test() throws ApiException {
		// Arrange
		String cardNumber = "008890";
		JsonApiBody<UserValidationRequest> jsonApiBody = new JsonApiBody<>();
		jsonApiBody.newDataInstance().setAttributes(aUserValidationRequest().build());
		User expectedUser = null;
		Double totalValueToPay = 100000d;

		JsonApiError expected = aJsonApiError()
				.with(anErrorDetail().withCode(this.yamlPropertiesMock.appErrorsUserNotFoundCode)).build();

		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expectedUser);
		JsonApiError actual = (JsonApiError) this.userServiceMock.userValidations(jsonApiBody, totalValueToPay);
		// Assert
		assertNotNull(actual.getErrors());
		assertTrue(actual.getErrors().size() > 0);
		assertEquals(expected.getErrors().get(0).getCode(), actual.getErrors().get(0).getCode());
	}

	@Test
	public void userValidations_card_is_null_test() throws ApiException {
		// Arrange
		String cardNumber = "008890";
		JsonApiBody<UserValidationRequest> jsonApiBody = new JsonApiBody<>();
		jsonApiBody.newDataInstance().setAttributes(aUserValidationRequest().build());
		User expectedUser = aUser().withCard(null).build();
		Double totalValueToPay = 100000d;

		JsonApiError expected = aJsonApiError()
				.with(anErrorDetail().withCode(this.yamlPropertiesMock.appErrorsCardNumberEmptyCode)).build();

		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expectedUser);
		JsonApiError actual = (JsonApiError) this.userServiceMock.userValidations(jsonApiBody, totalValueToPay);
		// Assert
		assertNotNull(actual.getErrors());
		assertTrue(actual.getErrors().size() > 0);
		assertEquals(expected.getErrors().get(0).getCode(), actual.getErrors().get(0).getCode());
	}
}
