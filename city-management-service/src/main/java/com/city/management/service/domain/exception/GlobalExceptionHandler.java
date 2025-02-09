package com.city.management.service.domain.exception;

import com.city.management.service.domain.constant.Constants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<Object> handleServiceException(RuntimeException ex, HttpServletRequest request) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    ApiError apiError = ApiError.builder()
        .errorId(httpStatus.name())
        .message(httpStatus.getReasonPhrase())
        .details(List.of(ApiErrorDetail.builder()
            .issue(ex.getMessage())
            .build()))
        .build();
    log.error("[{}:{}] GlobalExceptionHandler:: exception stacktrace={}", Constants.TECHNICAL_ERROR_TYPE, Constants.INTERNAL_ERROR_CODE, ex.getStackTrace());
    return new ResponseEntity<>(apiError, httpStatus);
  }
}
