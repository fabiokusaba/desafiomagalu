package com.fabiokusaba.desafiomagalu.infrastructure.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
