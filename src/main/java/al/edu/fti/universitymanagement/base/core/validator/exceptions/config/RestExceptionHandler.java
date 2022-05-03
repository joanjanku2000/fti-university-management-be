package al.edu.fti.universitymanagement.base.core.validator.exceptions.config;

import al.edu.fti.universitymanagement.base.core.validator.exceptions.BadRequestException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class, NotFoundException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest webRequest){
        return handleExceptionInternal(ex, new ErrorFormat(LocalDateTime.now(), ex.getMessage()), new HttpHeaders() ,
                ex instanceof BadRequestException ? HttpStatus.BAD_REQUEST : HttpStatus.NOT_FOUND , webRequest
                );
    }

    @Data @AllArgsConstructor
    private class ErrorFormat {
        private LocalDateTime timeOfError;
        private String errorMessage;
    }
}
