package com.wish.model;

import java.util.Date;

public class ExecuteResult<T> {
	
	private final long timeOut = 50000L;
	private boolean isSuccess;
	private T data;

	private String errorCode;
	private String errorMsg;
	private String fromUrl;

	private Long processTime;
	@SuppressWarnings("unused")
	private Long flushTimeOut;

	public T getData() {
		return data;
	}

	public String getErrorCode() {
		return errorCode == null ? "" : errorCode;
	}

	public String getErrorMsg() {
		return errorMsg == null ? "" : errorMsg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Date getFlushTime() {
		return new Date();
		// return flushTime;
	}

	public Long getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Long processTime) {
		this.processTime = processTime;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public Long getFlushTimeOut() {
		return timeOut;
		// return flushTimeOut;
	}

	public void setFlushTimeOut(Long flushTimeOut) {
		this.flushTimeOut = flushTimeOut;
	}

}
