package com.city.management.service.domain.exception;

public class ServiceException extends RuntimeException {
  private final String errorCode;

  public ServiceException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
