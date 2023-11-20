package cz.czechitas.java2webapps.ukol6.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class BusinessCardControllerExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public String handleResponseStatusException() {
        return "businessCardNotFoundErrorPage";
    }
}