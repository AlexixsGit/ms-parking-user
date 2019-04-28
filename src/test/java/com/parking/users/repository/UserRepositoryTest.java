package com.parking.users.repository;

import static com.parking.users.builders.UserBuilder.aUser;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.Search;
import com.parking.users.exceptions.ApiException;
import com.parking.users.model.User;
import com.parking.users.util.DatabaseApi;
import com.parking.users.util.YamlProperties;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

	@Mock
	private DatabaseApi databaseApiMock;

	@Mock
	private Search searchMock;

	@Mock
	private Database database;

	@Mock
	private YamlProperties yamlPropertiesMock;

	@InjectMocks
	private UserRepository repositoryMock;

	@Before
	public void setup() {
		this.yamlPropertiesMock.searchIndexByCardNumber = "users/cardNumber";
		when(this.databaseApiMock.getCloudantConnection()).thenReturn(database);
		when(database.search(this.yamlPropertiesMock.searchIndexByCardNumber)).thenReturn(searchMock);
		when(searchMock.includeDocs(true)).thenReturn(searchMock);

	}

	@Test
	public void getUserByCardNumber_test() throws ApiException {
		// Arrange
		String cardNumber = "008890";
		User expected = aUser().build();
		List<Object> result = new ArrayList<>();
		result.add(expected);
		// Act
		when(searchMock.query(any(), any())).thenReturn(result);
		User actual = this.repositoryMock.getUserByCardNumber(cardNumber);
		// Assert
		assertEquals(expected, actual);
	}

	@Test(expected = ApiException.class)
	public void getUserByCardNumber_throws_exception_test() throws ApiException {
		// Arrange
		String cardNumber = "008890";
		// Act
		when(searchMock.query(any(), any())).thenReturn(null);
		this.repositoryMock.getUserByCardNumber(cardNumber);
	}
}
