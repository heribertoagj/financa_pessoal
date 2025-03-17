package br.com.ttech.fp.backend.common.records;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Response {
    private Integer code;
    private String message;
    private LocalDateTime dateTime;
    private Object data;
    private Boolean isOperational;
}
