package com.tech.bolcala.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidInputException extends RuntimeException{

    private String message;
    private int status;
    private LocalDateTime timestamp;

    public InvalidInputException(String message) {
        super(message);
    }
}
