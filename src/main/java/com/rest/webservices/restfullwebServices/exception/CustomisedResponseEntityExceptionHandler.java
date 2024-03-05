package com.rest.webservices.restfullwebServices.exception;

import org.springframework.http.HttpHeaders;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
// class is to provide customized exception for all exception that we encounter in application 
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
  
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
	   ErrorDetails errordetails=new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
   }
	
	@Override
   protected ResponseEntity<Object>handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers , HttpStatusCode status ,WebRequest request ){
	   ErrorDetails errordetails=new ErrorDetails(LocalDate.now(),ex.getFieldError().getDefaultMessage(),request.getDescription(false));
   return new ResponseEntity(errordetails,HttpStatus.BAD_REQUEST);
   }
}
