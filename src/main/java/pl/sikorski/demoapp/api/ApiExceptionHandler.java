package pl.sikorski.demoapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleNoTFound(ProductNotFoundException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
