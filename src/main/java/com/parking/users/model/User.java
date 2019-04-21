package com.parking.users.model;

public class User {

	private String address;
	private Card card;
	private String email;
	private String lastName;
	private String name;
	private String phone;
	private String userName;
	private Vehicle vehicle;

	public User(String address, Card card, String email, String lastName, String name, String phone, String userName,
			Vehicle vehicle) {
		this.address = address;
		this.card = card;
		this.email = email;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
		this.userName = userName;
		this.vehicle = vehicle;
	}

	public User() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
