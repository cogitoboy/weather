package org.dale.applegate.web.api.v1;

import org.dale.applegate.exception.ResourceNotFoundException;
import org.dale.applegate.model.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	//TODO: Need to add general Exception handler
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ServiceError> resourceNotFound(ResourceNotFoundException ex) {
        ServiceError response = new ServiceError();
        response.setErrorCode("Not Found");
        response.setErrorMessage(ex.getMessage());
 
        return new ResponseEntity<ServiceError>(response, HttpStatus.NOT_FOUND);
    }
}
