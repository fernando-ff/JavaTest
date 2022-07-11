package javatest.cd2tec.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {
    private String title;
    private int status;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;
}
