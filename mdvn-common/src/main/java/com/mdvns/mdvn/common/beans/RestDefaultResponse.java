package com.mdvns.mdvn.common.beans;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class RestDefaultResponse {

	/* http状�?�码 */
	private String statusCode;
	/* 异常状�?�码 */
	private String responseCode;
	/* 提示信息 */
	private String responseMsg;
	/* 业务数据 */
	private Object responseBody;

	public RestDefaultResponse() {

	}

	public RestDefaultResponse(String responseCode, Object responseBody) {
		super();
		this.responseCode = responseCode;
		this.responseBody = responseBody;
	}

	public RestDefaultResponse(String statusCode, String responseCode) {
		super();
		this.statusCode = statusCode;
		this.responseCode = responseCode;
	}

	public RestDefaultResponse(String statusCode, String responseCode, String responseMsg, Object responseBody) {
		this.statusCode = statusCode;
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
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

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
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
