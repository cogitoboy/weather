package org.stalesoft.web.api.v1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.stalesoft.exception.DaoException;
import org.stalesoft.exception.ResourceNotFoundException;
import org.stalesoft.model.ServiceError;
import org.stalesoft.web.api.v1.ExceptionController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionControllerTest {

  private static final String EXCEPTION_MESSAGE = "Big Error, muy malo";

  @Mock
  ResourceNotFoundException resourceNotFoundException;

  @Mock
  DaoException daoException;

  @Mock
  Exception exception;

  @InjectMocks
  ExceptionController exceptionController;

  @Test
  public void testResourceNotFound() {

    when(resourceNotFoundException.getMessage()).thenReturn(EXCEPTION_MESSAGE);
    ResponseEntity<ServiceError> response =
        exceptionController.resourceNotFound(resourceNotFoundException);

    assertEquals(response.getBody().getErrorMessage(), EXCEPTION_MESSAGE);
    assertEquals(response.getBody().getErrorCode(), ExceptionController.NOT_FOUND_CODE);
  }

  @Test
  public void testDaoError() {

    when(daoException.getMessage()).thenReturn(EXCEPTION_MESSAGE);
    ResponseEntity<ServiceError> response = exceptionController.daoError(daoException);

    assertEquals(response.getBody().getErrorMessage(), EXCEPTION_MESSAGE);
    assertEquals(response.getBody().getErrorCode(), ExceptionController.SERVICE_UNAVAILABLE_CODE);
  }

  @Test
  public void testGeneralError() {

    when(exception.getMessage()).thenReturn(EXCEPTION_MESSAGE);
    ResponseEntity<ServiceError> response = exceptionController.generalError(exception);

    assertEquals(response.getBody().getErrorMessage(), EXCEPTION_MESSAGE);
    assertEquals(response.getBody().getErrorCode(), ExceptionController.UNEXPECTED_ERROR_CODE);
  }

}
