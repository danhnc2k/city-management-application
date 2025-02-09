package com.city.management.service.domain.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GlobalExceptionHandlerTest {

  @InjectMocks
  GlobalExceptionHandler globalExceptionHandler;

  @Test
  void handleServiceException_shouldReturnErrorResponseEntity() {
    RuntimeException ex = new RuntimeException("Test message");
    ResponseEntity<Object> response = globalExceptionHandler.handleServiceException(ex, new MockHttpServletRequest());
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    assertThat(response.getBody()).isInstanceOf(ApiError.class);
  }
}
