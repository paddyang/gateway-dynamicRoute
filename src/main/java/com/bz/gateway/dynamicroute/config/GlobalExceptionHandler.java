package com.bz.gateway.dynamicroute.config;

import com.bz.gateway.dynamicroute.entity.dto.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler{

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse illegalArgumentException(IllegalArgumentException e){
        return BaseResponse.build(400,e.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public BaseResponse runtimeException(RuntimeException e){
        return BaseResponse.build(400,e.getMessage());
    }
}
