package dev.pack.exception;

public class MaxQuotaReachedException extends RuntimeException{
    public MaxQuotaReachedException(String message) {
        super(message);
    }
}
