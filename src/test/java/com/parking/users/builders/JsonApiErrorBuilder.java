package com.parking.users.builders;

import static com.parking.users.builders.ErrorDetailBuilder.anErrorDetail;

import java.util.ArrayList;
import java.util.List;

import com.parking.users.jsonapi.ErrorDetail;
import com.parking.users.jsonapi.JsonApiError;

public class JsonApiErrorBuilder {

	private List<ErrorDetail> errors;

	public JsonApiErrorBuilder() {
		with(anErrorDetail());
	}

	public JsonApiErrorBuilder with(ErrorDetailBuilder... builders) {
		this.errors = new ArrayList<>();

		for (ErrorDetailBuilder builder : builders) {
			this.errors.add(builder.build());
		}
		return this;
	}

	public static JsonApiErrorBuilder aJsonApiError() {
		return new JsonApiErrorBuilder();
	}

	public JsonApiError build() {
		return new JsonApiError(this.errors);
	}
}
