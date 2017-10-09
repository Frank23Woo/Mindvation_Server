package com.mdvns.mdvn.common.utils;

import org.springframework.http.HttpStatus;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;

public class RestResponseUtil {

	/**
	 * 返回成功，传入返回体具体出參
	 * 
	 * @param object
	 * @return
	 */
	public static RestResponse success(Object object) {
		RestResponse restResponse = new RestResponse();
		restResponse.setStatusCode(HttpStatus.OK.toString());
		restResponse.setResponseCode("000");
		restResponse.setResponseMsg("SUCCESS");
		restResponse.setResponseBody(object);
		return restResponse;
	}

	/**
	 * 提供给部分不需要出參的接口
	 * 
	 * @return
	 */
	public static RestResponse success() {
		return success(null);
	}

	/**
	 * 自定义错误信息
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static RestResponse error(HttpStatus statusCode, String errorCode, String errorMsg) {
		RestResponse restResponse = new RestResponse();
		restResponse.setStatusCode(statusCode.toString());
		restResponse.setResponseCode(errorCode);
		restResponse.setResponseMsg(errorMsg);
		restResponse.setResponseBody(null);
		return restResponse;
	}

	public static RestResponse error(HttpStatus statusCode,String errorCode, Throwable e) {
		RestResponse restResponse = new RestResponse();
		restResponse.setStatusCode(statusCode.toString());
		restResponse.setResponseCode(errorCode );
		restResponse.setResponseMsg(e.getMessage());
		restResponse.setResponseBody(null);
		return restResponse;
	}
	
	/**
	 * 返回异常信息，在已知的范围内
	 * 
	 * @param exceptionEnum
	 * @return
	 */
	public static RestResponse error(HttpStatus statusCode, ExceptionEnum exceptionEnum) {
		RestResponse restResponse = new RestResponse();
		restResponse.setStatusCode(statusCode.toString());
		restResponse.setResponseCode(exceptionEnum.getErroCode());
		restResponse.setResponseMsg(exceptionEnum.getErrorMsg());
		restResponse.setResponseBody(null);
		return restResponse;
	}

}
