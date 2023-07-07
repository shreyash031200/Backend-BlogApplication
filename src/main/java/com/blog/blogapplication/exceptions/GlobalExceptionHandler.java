package com.blog.blogapplication.exceptions;

import com.blog.blogapplication.payloads.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.*;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException rs){
        String message=rs.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUsernameAndPassword.class)
    public ResponseEntity<String> invalidUsernameAndPasswordHandler(InvalidUsernameAndPassword rs){
        return new ResponseEntity<>("Invalid Username or Password",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> resp =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            resp.put(fieldName,defaultMessage);
        });
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }


}
