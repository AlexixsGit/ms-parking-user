package com.parking.users.jsonapi;

public class ErrorDetail {

	private String code;
	private String id;
	private String source;
	private String status;
	private String title;
	private String detail;

	public ErrorDetail(String code, String id, String source, String status, String title, String detail) {
		this.code = code;
		this.id = id;
		this.source = source;
		this.status = status;
		this.title = title;
		this.detail = detail;
	}

	public ErrorDetail() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
