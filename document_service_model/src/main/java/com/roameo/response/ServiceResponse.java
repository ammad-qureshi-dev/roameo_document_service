package com.roameo.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponse<T> implements Serializable {
  private boolean isSuccess;
  private transient T data;
  private UUID requestId;
  private LocalDateTime requestCompletedAt;
  private List<Object> errors;

  public static <T> ResponseEntity<ServiceResponse<T>> getServiceResponse(
      boolean isSuccess, T data, HttpStatus httpStatus) {
    var serviceResponse =
        ServiceResponse.<T>builder()
            .isSuccess(isSuccess)
            .data(data)
            .requestId(UUID.randomUUID())
            .requestCompletedAt(LocalDateTime.now())
            .build();

    return new ResponseEntity<>(serviceResponse, httpStatus);
  }

  public static <T> ResponseEntity<ServiceResponse<T>> getServiceResponse(
      boolean isSuccess, T data, HttpStatus httpStatus, List<Object> errors) {
    var serviceResponse =
        ServiceResponse.<T>builder()
            .isSuccess(isSuccess)
            .data(data)
            .requestId(UUID.randomUUID())
            .requestCompletedAt(LocalDateTime.now())
            .errors(errors)
            .build();

    return new ResponseEntity<>(serviceResponse, httpStatus);
  }
}
