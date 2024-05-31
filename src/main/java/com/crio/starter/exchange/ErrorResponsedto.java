package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponsedto {

    private String apiPath;

    private HttpStatus errorCode;

    private String errorMessage;

    private LocalDateTime errorTime;
}