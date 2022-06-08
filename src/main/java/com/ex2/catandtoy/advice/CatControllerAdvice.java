package com.ex2.catandtoy.advice;

import com.ex2.catandtoy.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class CatControllerAdvice {
    @ExceptionHandler(value = {CatCustomException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails errorHelper(Exception e) {
        return new ErrorDetails("An error occurred", e.getMessage());
    }
}
