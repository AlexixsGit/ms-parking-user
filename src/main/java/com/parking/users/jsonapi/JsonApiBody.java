package com.parking.users.jsonapi;

public class JsonApiBody<T> {

	private JsonApiData<T> data;

	public JsonApiData<T> getData() {
		return data;
	}

	public void setData(JsonApiData<T> data) {
		this.data = data;
	}

	public JsonApiBody<T> data(JsonApiData<T> jsonApiData) {
		this.data = jsonApiData;
		return this;
	}

	public JsonApiData<T> newDataInstance() {
		this.data = new JsonApiData<>();
		return this.data;
	}
}
