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
	
	SAPI_EXCEPTION("10001", "SAPI异常"),

	//project
	//所有的请求参数错误
	REQUEST_NOT_VALID("1300","请求参数错误"),
	PROJECT_NOT_FOUND("1301", "项目不存在!"),
	PROJECT_STAFF_NOT_CREATE("1302", "调用SAPI保存项目负责人数据失败"),
	PROJECT_TAG_NOT_CREATE("1303", "调用SAPI保存项目标签数据失败"),
	PROJECT_MODEL_NOT_CREATE("1304", "调用SAPI保存项目模块数据失败"),
	PROJECT_CHECKLIST_NOT_CREATE("1305", "调用SAPI保存项目checklist数据失败"),
	PROJECT_ATTCHURL_NOT_CREATE("1306", "调用SAPI保存项目附件数据失败"),
	PROJECT_BASEINFO_NOT_CREATE("1307", "调用SAPI保存项目基础信息数据失败"),
	PROJECT_STAFF_NOT_UPDATE("1308", "调用SAPI更改项目负责人数据失败"),
	PROJECT_TAG_NOT_UPDATE("1309", "调用SAPI更改项目标签数据失败"),
	PROJECT_MODEL_NOT_UPDATE("1310", "调用SAPI更改项目模块数据失败"),
	PROJECT_CHECKLIST_NOT_UPDATE("1311", "调用SAPI更改项目checklist数据失败"),
	PROJECT_CHECKLIST_STAFFDETAIL_NOT_RTRV("1312", "调用SAPI获取项目checklist中的人员详细信息时失败"),
	PROJECT_ATTCHURL_NOT_UPDATE("1313", "调用SAPI更改项目附件数据失败"),
	PROJECT_BASEINFO_NOT_UPDATE("1314", "调用SAPI更改项目基础信息数据失败"),
	PROJECT_DETAIL_BASEINFO_NOT_RTRV("1315", "项目不存在!"),
	PROJECT_DETAIL_STAFF_NOT_RTRV("1316", "调用SAPI获取项目detail负责人信息时失败"),
	PROJECT_DETAIL_TAG_NOT_RTRV("1317", "调用SAPI获取项目detail标签信息时失败"),
	PROJECT_DETAIL_MODEL_NOT_RTRV("1318", "调用SAPI获取项目detail模型信息时失败"),
	PROJECT_DETAIL_CHECKLIST_NOT_RTRV("1319", "调用SAPI获取项目detail Checklist信息时失败"),
	PROJECT_DETAIL_ATTCHURL_NOT_RTRV("1320", "调用SAPI获取项目detail附件信息时失败");





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
