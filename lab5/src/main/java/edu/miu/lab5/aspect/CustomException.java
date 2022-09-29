package edu.miu.lab5.aspect;

import edu.miu.lab5.exception.BannedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@RestController
public class CustomException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleConflict(BannedException e, HttpServletResponse response)
            throws IOException {

        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}