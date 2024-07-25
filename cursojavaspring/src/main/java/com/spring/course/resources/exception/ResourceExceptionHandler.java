package com.spring.course.resources.exception;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.course.services.exception.DatabaseException;
import com.spring.course.services.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;


/* @ControllerAdvice 
 *  Intercepta todas as excecões em todo aplicativo
 *  e lança a excecão do metodo abaixo
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:ss");
	
   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
	   String error = "Not founded!";
	   HttpStatus status = HttpStatus.NOT_FOUND;
	   StandardError se = new StandardError(Instant.now(), status, error, e.getMessage(), request.getRequestURI());
	   return ResponseEntity.status(status).body(se); 
   }
   
   @ExceptionHandler(DatabaseException.class)
   public ResponseEntity<StandardError> dataBase(DatabaseException e, HttpServletRequest request) {
	   String error = "Not founded!";
	   HttpStatus status = HttpStatus.BAD_REQUEST;
	   StandardError se = new StandardError(Instant.now(), status, error, e.getMessage(), request.getRequestURI());
	   return ResponseEntity.status(status).body(se); 
   }
}
