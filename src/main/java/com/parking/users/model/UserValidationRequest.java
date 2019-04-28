package com.parking.users.model;

public class UserValidationRequest {

	private String cardNumber;

	public UserValidationRequest(String cardNumber) {
		this.setCardNumber(cardNumber);
	}

	public UserValidationRequest() {
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
