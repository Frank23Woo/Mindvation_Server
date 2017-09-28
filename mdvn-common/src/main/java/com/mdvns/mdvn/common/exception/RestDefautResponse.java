package com.mdvns.mdvn.common.exception;

import com.google.gson.Gson;

public class RestDefautResponse {

	/* http状态码 */
	private String statusCode;
	/* 异常状态码 */
	private String responseCode;
	/* 提示信息 */
	private String message;
	/* 业务数据 */
	private Object responseBody;

	public RestDefautResponse() {

	}

	public RestDefautResponse(String responseCode, Object responseBody) {
		super();
		this.responseCode = responseCode;
		this.responseBody = responseBody;
	}

	public RestDefautResponse(String statusCode, String responseCode) {
		super();
		this.statusCode = statusCode;
		this.responseCode = responseCode;
	}

	public RestDefautResponse(String statusCode, String responseCode, String message, Object responseBody) {
		this.statusCode = statusCode;
		this.responseCode = responseCode;
		this.message = message;
		this.responseBody = responseBody;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	@Override
	public String toString() {
		if (null == this.responseBody) {
			this.setResponseBody(new Object());
		}

		Gson gson = new Gson();
		return gson.toJson(this);

	}
}
