package com.parking.users.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parking.users.exceptions.ApiException;
import com.parking.users.jsonapi.JsonApiBody;
import com.parking.users.jsonapi.JsonApiError;
import com.parking.users.model.UserValidationRequest;
import com.parking.users.model.UserValidationResponse;
import com.parking.users.service.IUserService;
import com.parking.users.util.Util;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@Value("${app.errors.card_number_empty.code}")
	private String carNumberEmptyCode;

	@PostMapping("/user/checkInuserValidation")
	public ResponseEntity<Object> checkInuserValidation(@RequestBody JsonApiBody<UserValidationRequest> request)
			throws ApiException {

		try {
			String cardNumber = request.getData().getAttributes().getCardNumber();

			if (StringUtils.isEmpty(cardNumber)) {
				JsonApiError errApiError = Util.buildError(request.getData().getId(), "0001",
						"El numero del carnet no es valido", String.valueOf(HttpStatus.BAD_REQUEST.value()),
						"/user/userValidate", "El numero del carnet no es valido");
				return new ResponseEntity<Object>(errApiError, HttpStatus.BAD_REQUEST);
			}

			boolean allSuccess = this.userService.userValidations(cardNumber, 0d);
			UserValidationResponse userValidationResponse = new UserValidationResponse();
			userValidationResponse.setValid(allSuccess);
			JsonApiBody<UserValidationResponse> response = new JsonApiBody<>();
			response.newDataInstance().setAttributes(userValidationResponse);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ApiException(e, request.getData().getId(), "/user/checkInuserValidation");
		}
	}
}
