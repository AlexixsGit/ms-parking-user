package com.parking.users.exceptions;

public class ApiException extends Exception {

	private static final long serialVersionUID = 3614525694818660160L;

	public ApiException(Exception e) {
		super(e);
	}

	public ApiException() {

	}
}
