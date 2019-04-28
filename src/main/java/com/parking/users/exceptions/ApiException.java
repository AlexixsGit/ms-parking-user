package com.parking.users.exceptions;

public class ApiException extends Exception {

	private static final long serialVersionUID = 3614525694818660160L;

	private String id;
	private String detail;
	private String source;

	public ApiException(Exception e) {
		super(e);
	}

	public ApiException(Exception e, String id, String source) {
		super(e);
		this.id = id;
		this.detail = e.getMessage();
		this.source = source;
	}

	public ApiException() {

	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
