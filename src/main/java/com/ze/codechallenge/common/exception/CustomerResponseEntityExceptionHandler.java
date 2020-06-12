package com.ze.codechallenge.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

@RestControllerAdvice
public class CustomerResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
				.timestamp(new Date())
				.message(recuperarMensagem(ex))
				.details(request.getDescription(false))
				.build();

		return new ResponseEntity<>(exceptionResponse, Optional.ofNullable(status).orElse(HttpStatus.BAD_REQUEST));
	}

	@ResponseBody
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<ExceptionResponse> handleBaseException(HttpServletRequest req, RuntimeException exception) {
		ResponseStatus responseStatusAnnotation = exception.getClass().getAnnotation(ResponseStatus.class);
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
				.timestamp(new Date())
				.message(exception.getMessage())
				.details(req.getRequestURI())
				.build();

		return new ResponseEntity<>(exceptionResponse, Optional.ofNullable(responseStatusAnnotation.value()).orElse(HttpStatus.BAD_REQUEST));
	}

	private String recuperarMensagem(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream()
				.map(ObjectError::getDefaultMessage)
				.collect(joining("<br>"));
	}
}
