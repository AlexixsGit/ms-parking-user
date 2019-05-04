package com.parking.users.builders;

import com.parking.users.model.UserValidationRequest;

public class UserValidationRequestBuilder {

	private String cardNumber;
	private Double totalValueToPay;

	public UserValidationRequestBuilder() {
		this.cardNumber = "008890";
		this.totalValueToPay = 100000d;
	}

	public UserValidationRequestBuilder withCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
		return this;
	}

	public UserValidationRequestBuilder withTotalValueToPay(Double totalValueToPay) {
		this.totalValueToPay = totalValueToPay;
		return this;
	}

	public static UserValidationRequestBuilder aUserValidationRequest() {
		return new UserValidationRequestBuilder();
	}

	public UserValidationRequest build() {
		return new UserValidationRequest(this.cardNumber, this.totalValueToPay);
	}

}
