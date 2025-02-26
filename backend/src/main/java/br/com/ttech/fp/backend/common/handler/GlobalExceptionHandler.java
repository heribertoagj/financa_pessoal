package br.com.ttech.fp.backend.common.handler;


import br.com.ttech.fp.backend.common.dto.ResponseDto;
import br.com.ttech.fp.backend.common.exception.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleGenericException(Exception ex) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseDto.setMessage(ex.getMessage());
        responseDto.setDescription(ex.getMessage());
        responseDto.setDateTime(LocalDateTime.now());
        responseDto.setIsOperational(true);
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ResponseDto> handleUserInvalid(RuntimeException re){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setCode(HttpStatus.BAD_REQUEST.value());
        responseDto.setMessage(re.getMessage());
        responseDto.setDescription(re.getMessage());
        responseDto.setDateTime(LocalDateTime.now());
        responseDto.setIsOperational(true);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

    }
}
