package com.parking.users.builders;

import org.springframework.http.HttpStatus;

import com.parking.users.jsonapi.ErrorDetail;

public class ErrorDetailBuilder {

	private String id;
	private String status;
	private String code;
	private String title;
	private String source;
	private String detail;

	public ErrorDetailBuilder() {
		this.id = "12345";
		this.status = String.valueOf(HttpStatus.BAD_GATEWAY.value());
		this.code = "0001";
		this.title = "The system does not response";
		this.source = "/user/getUserByCardNumber";
		this.detail = "El sistema no esta disponible";
	}

	public ErrorDetailBuilder withId(String id) {
		this.id = id;
		return this;
	}

	public ErrorDetailBuilder withStatus(String status) {
		this.status = status;
		return this;
	}

	public ErrorDetailBuilder withCode(String code) {
		this.code = code;
		return this;
	}

	public ErrorDetailBuilder withTitle(String title) {
		this.title = title;
		return this;
	}

	public ErrorDetailBuilder withSource(String source) {
		this.source = source;
		return this;
	}

	public ErrorDetailBuilder withDetail(String detail) {
		this.detail = detail;
		return this;
	}

	public static ErrorDetailBuilder anErrorDetail() {
		return new ErrorDetailBuilder();
	}

	public ErrorDetail build() {
		return new ErrorDetail(this.code, this.id, this.source, this.status, this.title, this.detail);
	}

}
