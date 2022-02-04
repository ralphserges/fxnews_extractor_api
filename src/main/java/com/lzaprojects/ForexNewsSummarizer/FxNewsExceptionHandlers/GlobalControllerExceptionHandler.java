package com.lzaprojects.ForexNewsSummarizer.FxNewsExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalServerException.class)
	public ExceptionResponse handleInternalServerError(InternalServerException ise) {
		return new ExceptionResponse(ise.getTimeStamp(), ise.getErrorCode().value(), ise.getErrorMsg());
	}
	
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(InvalidInputException.class)
	public ExceptionResponse handleFxKeyNotFound(InvalidInputException iie) {
		return new ExceptionResponse(iie.getTimeStamp(),iie.getErrorCode().value(),iie.getErrorMsg());
	}
	
}
