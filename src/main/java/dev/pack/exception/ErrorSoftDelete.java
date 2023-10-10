package dev.pack.exception;

public class ErrorSoftDelete extends RuntimeException{
    public ErrorSoftDelete(String message) {
        super(message);
    }
}
