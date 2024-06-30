package com.nagarro.activation.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler {

	   /**
     * Handles CustomNotFoundException and maps it to an error response with a specific HTTP status.
     * @param ex The Exception thrown
     * @return ResponseEntity containing the error response and HTTP status
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleNotFoundException(CustomException ex) {
    	
    	// Creating an error response object with  status code and error message
        ErrorResponse errorResponse = new ErrorResponse(
               
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        
        // Returning a ResponseEntity with the error response and NOT_FOUND status
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    
}