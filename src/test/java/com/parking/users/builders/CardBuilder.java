package com.parking.users.builders;

import java.util.Date;

import com.parking.users.model.Card;

public class CardBuilder {

	private Double balance;
	private String number;
	private Date expirationDate;

	public CardBuilder() {
		this.balance = 200000d;
		this.number = "008890";
		this.expirationDate = new Date();
	}

	public CardBuilder withBalance(Double balance) {
		this.balance = balance;
		return this;
	}

	public CardBuilder withNumber(String number) {
		this.number = number;
		return this;
	}

	public CardBuilder withExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
		return this;
	}

	public static CardBuilder aCard() {
		return new CardBuilder();
	}

	public Card build() {
		return new Card(this.balance, this.expirationDate, this.number);
	}

}
