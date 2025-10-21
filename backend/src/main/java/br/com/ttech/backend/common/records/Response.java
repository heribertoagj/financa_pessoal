package br.com.ttech.backend.common.records;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
