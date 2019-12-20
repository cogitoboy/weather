package org.stalesoft.web.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.stalesoft.exception.DaoException;
import org.stalesoft.exception.ResourceNotFoundException;
import org.stalesoft.model.ServiceError;


public class ExceptionController {

  public static final String NOT_FOUND_CODE = "Not Found";
  public static final String SERVICE_UNAVAILABLE_CODE = "Service Error";
  public static final String UNEXPECTED_ERROR_CODE = "Unexpected System Error";

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ServiceError> resourceNotFound(ResourceNotFoundException ex) {
    ServiceError response = new ServiceError();
    response.setErrorCode(NOT_FOUND_CODE);
    response.setErrorMessage(ex.getMessage());

    return new ResponseEntity<ServiceError>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DaoException.class)
  public ResponseEntity<ServiceError> daoError(DaoException ex) {
    ServiceError response = new ServiceError();
    response.setErrorCode(SERVICE_UNAVAILABLE_CODE);
    response.setErrorMessage(ex.getMessage());

    return new ResponseEntity<ServiceError>(response, HttpStatus.SERVICE_UNAVAILABLE);

  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ServiceError> generalError(Exception ex) {
    ServiceError response = new ServiceError();
    response.setErrorCode(UNEXPECTED_ERROR_CODE);
    response.setErrorMessage(ex.getMessage());

    return new ResponseEntity<ServiceError>(response, HttpStatus.INTERNAL_SERVER_ERROR);

  }

}
