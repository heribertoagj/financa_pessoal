package br.com.ttech.fp.backend.common.exception;

public class RequiredFieldException extends RuntimeException{
    public RequiredFieldException(String message){
        super(message);
    }
}
