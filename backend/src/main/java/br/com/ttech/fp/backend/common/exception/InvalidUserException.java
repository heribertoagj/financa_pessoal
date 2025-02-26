package br.com.ttech.fp.backend.common.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {

      super(message);
    }
}
