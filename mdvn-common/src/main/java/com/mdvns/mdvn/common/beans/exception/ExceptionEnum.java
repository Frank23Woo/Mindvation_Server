package com.mdvns.mdvn.common.beans.exception;

public enum ExceptionEnum {
	
	/**
	 * errorCode: 每个模块在一定范围定义
	 * 
	 * 
	 * 1000~1100 Tag 
	 * 1100~1200 PCheckList
	 * 1200~1300 Model
	 * 1300~1400 Project
	 */
	UNKNOW_EXCEPTION("555","未知错误"),

	//标签
	TAG_NOT_FOUND("1000", "Tag不存在!"),
	TAG_IS_CREATED("1001", "Tag已存在!"),

	//模块
	Model_NOT_FOUND("1200", "模块不存在!"),
	Model_IS_CREATED("1201", "模块已存在!"),

	//用户
	USER_NOT_FOUND("101", "用户不存在!"),
	
	SAPI_EXCEPTION("10001", "SAPI异常");



	
	

		String erroCode;
		String ErrorMsg;
		
		private ExceptionEnum(String erroCode, String errorMsg) {
			this.erroCode = erroCode;
			ErrorMsg = errorMsg;
		}
		public String getErroCode() {
			return erroCode;
		}
		public void setErroCode(String erroCode) {
			this.erroCode = erroCode;
		}
		public String getErrorMsg() {
			return ErrorMsg;
		}
		public void setErrorMsg(String errorMsg) {
			ErrorMsg = errorMsg;
		}
		
		
		
}
