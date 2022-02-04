package com.lzaprojects.ForexNewsSummarizer.FxNewsExceptionHandlers;


public class ExceptionResponse {
	private String timeStamp;
	private int errorCode;
	private String errorMsg;
	
	public ExceptionResponse(String timeStamp, int errorCode, String errorMsg) {
		this.timeStamp = timeStamp;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	
}
