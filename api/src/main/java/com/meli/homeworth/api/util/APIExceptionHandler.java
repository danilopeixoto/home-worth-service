package com.meli.homeworth.api.util;

import com.meli.homeworth.api.model.ErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.util.NoSuchElementException;
import java.util.Objects;

@RestControllerAdvice
public class APIExceptionHandler {
  @ExceptionHandler({
    IllegalArgumentException.class,
    ServerWebInputException.class
  })
  public ResponseEntity<ErrorResponseModel> handleBadRequest(Exception exception) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new ErrorResponseModel(
        exception instanceof WebExchangeBindException ?
          Objects.requireNonNull(
            ((WebExchangeBindException) exception)
              .getFieldError())
            .getDefaultMessage() :
          exception.getMessage()));
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorResponseModel> handleNotFound(Exception exception) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(new ErrorResponseModel(exception.getMessage()));
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ErrorResponseModel> handleConflict(Exception exception) {
    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(new ErrorResponseModel(exception.getMessage()));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponseModel> handleInternalServerError(Exception exception) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(new ErrorResponseModel("Internal server error."));
  }
}
