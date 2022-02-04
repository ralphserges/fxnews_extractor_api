package com.lzaprojects.ForexNewsSummarizer.FxNewsExceptionHandlers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String timeStamp;
	private HttpStatus errorCode;
	private String errorMsg;
	
	public InvalidInputException(HttpStatus errorCode, String errorMsg) {
		this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public HttpStatus getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
