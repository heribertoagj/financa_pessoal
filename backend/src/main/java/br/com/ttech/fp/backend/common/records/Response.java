package br.com.ttech.fp.backend.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResponseDto {
    private Integer code;
    private String message;
    private LocalDateTime dateTime;
    private Object data;
    private Boolean isOperational;
}
