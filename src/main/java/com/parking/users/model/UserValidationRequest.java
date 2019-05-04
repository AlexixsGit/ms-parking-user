package com.parking.users.model;

public class UserValidationRequest {

	private String cardNumber;
	private Double totalValueToPay;

	public UserValidationRequest(String cardNumber, Double totalValueToPay) {
		this.setCardNumber(cardNumber);
		this.setTotalValueToPay(totalValueToPay);
	}

	public UserValidationRequest() {
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getTotalValueToPay() {
		return totalValueToPay;
	}

	public void setTotalValueToPay(Double totalValueToPay) {
		this.totalValueToPay = totalValueToPay;
	}

}
