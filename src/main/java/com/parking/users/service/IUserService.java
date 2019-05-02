package com.parking.users.service;

import com.parking.users.exceptions.ApiException;
import com.parking.users.jsonapi.JsonApiBody;
import com.parking.users.model.UserValidationRequest;

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
	 * User validations
	 * 
	 * @param request
	 * @param totalValueToPay
	 * @return
	 * @throws ApiException
	 */
	public Object userValidations(JsonApiBody<UserValidationRequest> request, double totalValueToPay)
			throws ApiException;
}
