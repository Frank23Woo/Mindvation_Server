package com.mdvns.mdvn.common.beans;

import com.google.gson.Gson;
import com.mdvns.mdvn.common.beans.exception.ReturnFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestResponse<T> {

	/* http状态码 */
	private String statusCode;
	/* 异常状态码 */
	private String responseCode;
	/* 提示信息 */
	private String responseMsg;
	/* 业务数据 */
	private T responseBody;

	public RestResponse() {

	}

	public RestResponse(String responseCode, T responseBody) {
		super();
		this.responseCode = responseCode;
		this.responseBody = responseBody;
	}

	public RestResponse(String statusCode, String responseCode) {
		super();
		this.statusCode = statusCode;
		this.responseCode = responseCode;
	}

	public RestResponse(String statusCode, String responseCode, String responseMsg, T responseBody) {
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

	public T getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(T responseBody) {
		this.responseBody = responseBody;
	}
	public T getResponseBody(Object object) {
	       return responseBody;
	   }
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("<");
		if (this.responseBody != null) {
			builder.append(this.responseBody);
		}
		builder.append('>');
		return builder.toString();
	}

}
