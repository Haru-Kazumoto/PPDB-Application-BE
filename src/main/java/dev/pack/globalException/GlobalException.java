package dev.pack.globalException;

import dev.pack.exception.DataNotFoundException;
import dev.pack.payloads.ErrorResponse;
import dev.pack.payloads.ValidationErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    private ErrorResponse errorResponse(String message, Date timestamps, Integer statusCode){
        return ErrorResponse.builder()
                .message(message)
                .timestamps(timestamps)
                .statusCode(statusCode)
                .build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ErrorResponse err = errorResponse(ex.getMessage(), new Date(), statusCode.value());
        return new ResponseEntity<>(err, headers, statusCode);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ErrorResponse err = errorResponse(ex.getMessage(), new Date(), statusCode.value());
        return new ResponseEntity<>(err, headers, statusCode);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFound(DataNotFoundException ex, HttpHeaders headers, HttpStatusCode statusCode){
        ErrorResponse err = errorResponse(ex.getMessage(), new Date(), statusCode.value());
        return new ResponseEntity<>(err, statusCode);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse err = errorResponse(ex.getMessage(), new Date(), status.value());
        return new ResponseEntity<>(err, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse err = errorResponse(ex.getMessage(), new Date(), status.value());
        return new ResponseEntity<>(err, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse err = errorResponse(ex.getMessage(), new Date(), status.value());
        return new ResponseEntity<>(err, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();

        ex
                .getAllErrors()
                .forEach(
                        err -> errors.add(err.getDefaultMessage())
                );

        ValidationErrorResponse result = ValidationErrorResponse.builder()
                .statusCode(status.value())
                .messages(errors)
                .build();

        return new ResponseEntity<>(result, headers, status);
    }
}
