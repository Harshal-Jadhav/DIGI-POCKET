package com.project.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Add Your Exceptions Here:------------->

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> customerException(CustomerException e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BeneficiaryNotFoundException.class)
	public ResponseEntity<ErrorDetails> beneficiaryNotFoundException(BeneficiaryNotFoundException e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	
	// BankAccount Exception added by Suresh
	
	@ExceptionHandler(BankAccountException.class)
	public ResponseEntity<ErrorDetails> accountNotFoundException(BankAccountException e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	// End of User Defined Exceptions:------->
	
	

	// Default Exceptions Listed Below:----->
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> NoHandlerFoundException(NoHandlerFoundException e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		ErrorDetails err = new ErrorDetails(LocalDateTime.now(), "Validation Error",
				me.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> ExceptionHandler(Exception e, WebRequest wr) {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}

}
