package org.stalesoft.exception;

public class DaoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private Long resourceId;

  public DaoException(Long resourceId, String message) {
    super(message);
    this.resourceId = resourceId;
  }

  public Long getResourceId() {
    return resourceId;
  }
}
