package com.crio.starter.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.starter.exchange.ErrorResponsedto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
            ){
    Map<String,String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach(
                (error)->{
                    String fieldName = ((FieldError) error).getField();
                    String valiidationMsg = error.getDefaultMessage();
                    validationErrors.put(fieldName,valiidationMsg);
                }
        );
        return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MemeAlreadyExistException.class)
    public ResponseEntity<ErrorResponsedto> handleCustomerAlreadyExistsException(
            MemeAlreadyExistException exception, WebRequest webRequest
            ){
        ErrorResponsedto errorResponsedto = new ErrorResponsedto(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponsedto,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponsedto> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ){
        ErrorResponsedto errorResponsedto = new ErrorResponsedto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponsedto,HttpStatus.BAD_REQUEST);
    }
}
