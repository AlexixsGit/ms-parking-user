package com.parking.users.jsonapi;

public class JsonApiBody<T> {

	private JsonApiData<T> data;

	public JsonApiData<T> getData() {
		return data;
	}

	public void setData(JsonApiData<T> data) {
		this.data = data;
	}
}
