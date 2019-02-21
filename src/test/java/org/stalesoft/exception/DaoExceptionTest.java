package org.stalesoft.exception;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.stalesoft.exception.DaoException;

public class DaoExceptionTest {
  public static final Long RESOURCE_ID = 1L;
  private static final String EXCEPTION_MESSAGE = "Big Error, muy malo";

  
  @Test
  public void test() {
    DaoException ex = new DaoException(RESOURCE_ID, EXCEPTION_MESSAGE);
    assertEquals(RESOURCE_ID, ex.getResourceId());
  }

}
