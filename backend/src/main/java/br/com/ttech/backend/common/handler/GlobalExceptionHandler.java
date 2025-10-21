package br.com.ttech.backend.common.handler;

import br.com.ttech.backend.common.enums.Messages;
import br.com.ttech.backend.common.exception.BadRequestException;
import br.com.ttech.backend.common.exception.NotFoundException;
import br.com.ttech.backend.common.records.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGenericException(Exception ex) {
        Response response = new Response();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Occurred unexpected error!");
        response.setDateTime(LocalDateTime.now());
        response.setIsOperational(true);
        response.setData(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response> handleBadCredentialsException(BadCredentialsException bce) {
        Response response = new Response();
        response.setCode(HttpStatus.FORBIDDEN.value());
        response.setMessage(Messages.USERNAME_OR_PASSWORD_IS_INVALID.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setIsOperational(true);
        response.setData(bce.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handlerBadRequestException(BadRequestException rfe) {
        Response response = new Response();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(rfe.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setIsOperational(true);
        response.setData(rfe.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handlerNotFoundException(NotFoundException nfe){
        Response response = new Response();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(nfe.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setIsOperational(true);
        response.setData(nfe.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Response> handlerMissingParameterException(MissingServletRequestParameterException msrpe){
        Response response = new Response();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(Messages.REQUIRED_PARAMETER_IS_MISSING.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setIsOperational(true);
        response.setData(msrpe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}