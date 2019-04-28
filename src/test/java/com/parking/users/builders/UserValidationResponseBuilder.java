package com.parking.users.builders;

import com.parking.users.model.UserValidationResponse;

public class UserValidationResponseBuilder {

	private boolean isValid;

	public UserValidationResponseBuilder() {
		this.isValid = true;
	}

	public UserValidationResponseBuilder withIsValid(boolean isValid) {
		this.isValid = isValid;
		return this;
	}

	public static UserValidationResponseBuilder aUserValidationResponse() {
		return new UserValidationResponseBuilder();
	}

	public UserValidationResponse build() {
		return new UserValidationResponse(this.isValid);
	}
}
