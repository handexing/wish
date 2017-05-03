package com.wish.model;

public enum ErrorCode {

	TOKEN_INVALID("令牌失效"), 
	USER_NOT_REGISTED("用户未注册"), 
	PARAM_MISS("参数缺失"), 
	PARAM_FORMAT_ERROR("参数格式不正确"), 
	EXCEPTION("程序异常"),
	USERNAME_OR_PWD_ERROR("用户名或密码不正确"), 
	USER_EXIST("用户已存在"), 
	USER_NOT_EXIST("用户不存在"), 
	OBJ_NOT_EXIST("对象不存在"), 
	ILLEGAL_ARGUMENT("无效参数"), 
	CODE_EXPIRED("无效验证码"),
	MOBILE_EXISTED("该手机号已注册");
	
	private String errorMsg;

	private ErrorCode(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
