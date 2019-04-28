package com.parking.users.jsonapi;

import java.util.ArrayList;
import java.util.List;

public class JsonApiError {

	List<ErrorDetail> errors = new ArrayList<>();

	public JsonApiError(List<ErrorDetail> errors) {
		this.errors = errors;
	}

	public JsonApiError() {
	}

	public void addErrorDetail(ErrorDetail errorDetail) {
		this.errors.add(errorDetail);
	}

	public List<ErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetail> errors) {
		this.errors = errors;
	}

}
