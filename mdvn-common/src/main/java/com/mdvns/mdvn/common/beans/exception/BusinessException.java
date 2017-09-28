package com.mdvns.mdvn.common.beans.exception;

public class BusinessException extends Exception {

	private String errorCode;

	private String errorMsg;

	public BusinessException(Object Obj) {
		super(Obj.toString());
	}

	public BusinessException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "BusinessException [errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
	}

}
