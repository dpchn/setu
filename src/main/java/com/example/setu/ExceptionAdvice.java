package com.example.setu;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.setu.pojo.ApiResponse;
import com.example.setu.pojo.CustomErrorResponse;

/**
 * Global exception to handle exception
 * @author dpchn
 *
 */
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(Throwable.class)
	public @ResponseBody ResponseEntity<CustomErrorResponse> handleDefaultExcption(Throwable ex) {
		CustomErrorResponse error = new CustomErrorResponse("unhandled-error", "ERROR");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public @ResponseBody ResponseEntity<CustomErrorResponse> authExcption(Throwable ex) {
		CustomErrorResponse error = new CustomErrorResponse("auth-error", "ERROR");
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<?> handleMissingRequestBody(Exception ex) {
		CustomErrorResponse error = new CustomErrorResponse("invalid-api-parameters", "ERROR");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ResponseEntity<CustomErrorResponse> handleDefaultException(Throwable ex) {
		CustomErrorResponse error = new CustomErrorResponse("invalid-api-parameters", "ERROR");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
		CustomErrorResponse error = new CustomErrorResponse("path-not-found", "ERROR");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// @ExceptionHandler(NotFoundException.class)

	@ResponseBody
	ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request);
		return new ResponseEntity<>(new ApiResponse(300, "hello", "fail"), HttpStatus.ACCEPTED);
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		System.out.println("Hello :" + request.getHeader("apiKey"));
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}

}