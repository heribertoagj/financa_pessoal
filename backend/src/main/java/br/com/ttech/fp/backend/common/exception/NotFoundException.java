package br.com.ttech.fp.backend.common.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}

