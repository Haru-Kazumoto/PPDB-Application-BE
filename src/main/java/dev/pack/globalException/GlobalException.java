package dev.pack.globalException;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.exception.MaxQuotaReachedException;
import dev.pack.payloads.ErrorResponse;
import dev.pack.payloads.ValidationErrorResponse;
import lombok.NonNull;
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
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusResponse(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message("Error server, silahkan hubungi admin atau tunggu beberapa saat")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialError(BadCredentialsException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusResponse(HttpStatus.UNAUTHORIZED.name())
                .message("Username atau password salah")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ErrorResponse> handleViolatesConstraints(DuplicateDataException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusResponse(HttpStatus.BAD_REQUEST.name())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaxQuotaReachedException.class)
    public ResponseEntity<ErrorResponse> handleViolatesConstraints(MaxQuotaReachedException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusResponse(HttpStatus.BAD_REQUEST.name())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusResponse(status.toString())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleObjectNotFound(DataNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusResponse("NOT FOUND")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        ValidationErrorResponse response = new ValidationErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                Collections.singletonList(ex.getMessage())
        );
        return new ResponseEntity<>(response, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        ValidationErrorResponse response = new ValidationErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                Collections.singletonList(ex.getMessage())
        );
        return new ResponseEntity<>(response, headers, status);
    }
}
