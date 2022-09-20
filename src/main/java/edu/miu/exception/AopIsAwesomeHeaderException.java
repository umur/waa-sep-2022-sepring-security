package edu.miu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//
@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "AOP is awesome in header.")
public class AopIsAwesomeHeaderException extends RuntimeException{
    public AopIsAwesomeHeaderException(String message) {
        super(message);
    }
}
