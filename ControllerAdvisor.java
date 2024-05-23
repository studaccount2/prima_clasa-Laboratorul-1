package ro.uaic.Lab11.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.uaic.Lab11.exceptions.GurobiException;
import ro.uaic.Lab11.exceptions.ServerException;

@RestControllerAdvice
public class ControllerAdvisor {
    @ResponseBody
    @ExceptionHandler({ServerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String serverExceptionHandler(ServerException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({GurobiException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String gurobiExceptionHandler(GurobiException ex) {
        return ex.getMessage();
    }

}
