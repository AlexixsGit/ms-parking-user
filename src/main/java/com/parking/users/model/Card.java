package com.parking.users.model;

import java.util.Date;

public class Card {

	private Double balance;
	private Date expirationDate;
	private String number;

	public Card(Double balance, Date expirationDate, String number) {
		this.balance = balance;
		this.expirationDate = expirationDate;
		this.number = number;
	}

	public Card() {
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
}
