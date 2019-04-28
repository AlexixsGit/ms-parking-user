package com.parking.users.model;

public class UserValidationResponse {

	private boolean isValid;

	public UserValidationResponse(boolean isValid) {
		this.setValid(isValid);
	}

	public UserValidationResponse() {
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
