package com.parking.users.service;

import com.parking.users.exceptions.ApiException;

public interface IUserService {

	/**
	 * Validate if a user exists by the specified card number
	 * 
	 * @param cardNumber
	 * @return boolean
	 * @throws ApiException
	 */
	public boolean checkIfUserByCardNumberExists(String cardNumber) throws ApiException;

	/**
	 * Executes the validations required for a user
	 * 
	 * @param cardNumber
	 * @param totalValueToPay
	 * @return
	 * @throws ApiException
	 */
	public boolean userValidations(String cardNumber, Double totalValueToPay) throws ApiException;
}
