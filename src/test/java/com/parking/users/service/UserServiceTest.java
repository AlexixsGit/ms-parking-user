package com.parking.users.service;

import static com.parking.users.builders.CardBuilder.aCard;
import static com.parking.users.builders.UserBuilder.aUser;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.parking.users.exceptions.ApiException;
import com.parking.users.model.User;
import com.parking.users.repository.IUserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private IUserRepository userRepositoryMock;

	@InjectMocks
	private UserService userServiceMock;

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
		User expectedUser = aUser().build();
		Double totalValueToPay = 100000d;
		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expectedUser);
		boolean validationsSuccess = this.userServiceMock.userValidations(cardNumber, totalValueToPay);
		// Assert
		assertTrue(validationsSuccess);
	}

	@Test
	public void userValidations_user_exists_and_does_not_has_balance_test() throws ApiException {
		// Arrange
		String cardNumber = "008890";
		User expectedUser = aUser().build();
		Double totalValueToPay = 300000d;
		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expectedUser);
		boolean validationsSuccess = this.userServiceMock.userValidations(cardNumber, totalValueToPay);
		// Assert
		assertFalse(validationsSuccess);
	}

	@Test
	public void userValidations_user_not_exists_test() throws ApiException {
		// Arrange
		String cardNumber = "008890";
		User expectedUser = null;
		Double totalValueToPay = 100000d;
		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expectedUser);
		boolean validationsSuccess = this.userServiceMock.userValidations(cardNumber, totalValueToPay);
		// Assert
		assertFalse(validationsSuccess);
	}

	@Test
	public void userValidations_card_is_null_test() throws ApiException {
		// Arrange
		String cardNumber = "008890";
		User expectedUser = aUser().withCard(null).build();
		Double totalValueToPay = 100000d;
		// Act
		when(this.userRepositoryMock.getUserByCardNumber(cardNumber)).thenReturn(expectedUser);
		boolean validationsSuccess = this.userServiceMock.userValidations(cardNumber, totalValueToPay);
		// Assert
		assertFalse(validationsSuccess);
	}
}
