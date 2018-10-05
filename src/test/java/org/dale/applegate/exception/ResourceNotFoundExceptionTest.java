package org.dale.applegate.exception;

import static org.junit.Assert.*;
import org.junit.Test;

public class ResourceNotFoundExceptionTest {

  public static final Long RESOURCE_ID = 1L;
  private static final String EXCEPTION_MESSAGE = "Big Error, muy malo";

  
  @Test
  public void test() {
    ResourceNotFoundException ex = new ResourceNotFoundException(RESOURCE_ID, EXCEPTION_MESSAGE);
    assertEquals(RESOURCE_ID, ex.getResourceId());
  }

}
