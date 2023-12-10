package com.example.MyWeb.demo.handler;


import com.example.MyWeb.demo.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//에러 화면 처리
@ControllerAdvice //모든 익셉션이 발생하면 해당 어노테이션이 있는곳으로 온다.
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value= Exception.class)
    public ResponseDto<String> handleArgumentException(IllegalArgumentException e){
        return  new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());

    }
}
