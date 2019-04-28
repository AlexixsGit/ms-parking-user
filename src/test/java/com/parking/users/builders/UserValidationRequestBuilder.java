package com.parking.users.builders;

import com.parking.users.model.UserValidationRequest;

public class UserValidationRequestBuilder {

	private String cardNumber;

	public UserValidationRequestBuilder() {
		this.cardNumber = "008890";
	}

	public UserValidationRequestBuilder withCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
		return this;
	}

	public static UserValidationRequestBuilder aUserValidationRequest() {
		return new UserValidationRequestBuilder();
	}

	public UserValidationRequest build() {
		return new UserValidationRequest(this.cardNumber);
	}

}
