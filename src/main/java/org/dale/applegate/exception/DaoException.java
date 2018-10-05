package org.dale.applegate.exception;

public class DaoException extends RuntimeException {
  private Long resourceId;

  public DaoException(Long resourceId, String message) {
    super(message);
    this.resourceId = resourceId;

  }

  public Long getResourceId() {
    return resourceId;
  }

}
