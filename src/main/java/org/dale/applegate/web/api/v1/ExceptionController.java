package org.dale.applegate.web.api.v1;

import org.dale.applegate.exception.DaoException;
import org.dale.applegate.exception.ResourceNotFoundException;
import org.dale.applegate.model.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	//TODO: Need To add test file
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ServiceError> resourceNotFound(ResourceNotFoundException ex) {
        ServiceError response = new ServiceError();
        response.setErrorCode("Not Found");
        response.setErrorMessage(ex.getMessage());
 
        return new ResponseEntity<ServiceError>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<ServiceError> daoError(DaoException ex) {
		ServiceError response = new ServiceError();
        response.setErrorCode("Service Error");
        response.setErrorMessage(ex.getMessage());
 
        return new ResponseEntity<ServiceError>(response, HttpStatus.SERVICE_UNAVAILABLE);
 		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServiceError> generalError(Exception ex) {
			ServiceError response = new ServiceError();
	        response.setErrorCode("System Error");
	        response.setErrorMessage(ex.getMessage());
	 
	        return new ResponseEntity<ServiceError>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	 		
		}
		
	
}
