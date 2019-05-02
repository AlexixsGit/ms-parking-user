package com.parking.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.parking.users.exceptions.ApiException;
import com.parking.users.jsonapi.JsonApiBody;
import com.parking.users.model.User;
import com.parking.users.model.UserValidationRequest;
import com.parking.users.repository.IUserRepository;
import com.parking.users.util.Util;
import com.parking.users.util.YamlProperties;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private YamlProperties yamlProperties;

	@Override
	public boolean checkIfUserByCardNumberExists(String cardNumber) throws ApiException {

		User user = this.userRepository.getUserByCardNumber(cardNumber);

		if (user == null) {
			return false;
		}
		try {
			return user.getCard().getNumber().equals(cardNumber);
		} catch (Exception e) {
			throw new ApiException(e);
		}
	}

	@Override
	public Object userValidations(JsonApiBody<UserValidationRequest> request, double totalValueToPay)
			throws ApiException {

		String errorCode = this.yamlProperties.appErrorsUserNotFoundCode;
		String errorMessage = this.yamlProperties.appErrorsUserNotFoundMessage;
		boolean isValid = true;

		User user = this.userRepository.getUserByCardNumber(request.getData().getAttributes().getCardNumber());

		if (user == null) {
			isValid = false;
		} else if (user.getCard() == null) {
			isValid = false;
			errorCode = this.yamlProperties.appErrorsCardNumberEmptyCode;
			errorMessage = this.yamlProperties.appErrorsCardNumberEmptyMessage;
		} else if (user.getCard().getBalance() < totalValueToPay) {
			isValid = false;
			errorCode = this.yamlProperties.appErrorsUserWithoutBalanceCode;
			errorMessage = this.yamlProperties.appErrorsUserWithoutBalanceMessage;
		}

		if (!isValid) {
			return Util.buildError(errorCode, errorCode, this.yamlProperties.appErrorsUserValidationRequiredTitle,
					String.valueOf(HttpStatus.FAILED_DEPENDENCY.value()), this.yamlProperties.appPathUserValidation,
					errorMessage);
		}

		return user;
	}

}
