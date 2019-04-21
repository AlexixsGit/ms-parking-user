package com.parking.users.repository;

import com.parking.users.exceptions.ApiException;
import com.parking.users.model.User;

public interface IUserRepository {

	/**
	 * Method to get a user by specified number
	 * 
	 * @param cardNumber
	 * @return
	 * @throws ApiException
	 */
	public User getUserByCardNumber(String cardNumber) throws ApiException;
}
