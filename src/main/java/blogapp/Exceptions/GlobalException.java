package blogapp.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalException {

    private static Exception exception;

    @ExceptionHandler(BlogException.class)
    public static ResponseEntity<AllErrorDetails> exceptionHandler(BlogException blogEx, WebRequest wr){
        AllErrorDetails error = new AllErrorDetails();
        error.setTimeStamp(LocalDateTime.now());
        error.setDetails(wr.getDescription(false));
        error.setHttpStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(blogEx.getMessage());
        return new ResponseEntity<AllErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public static ResponseEntity<Object> exceptionHandler2(ConstraintViolationException ex, WebRequest wr){

        AllErrorDetails allError = new AllErrorDetails();
        allError.setHttpStatus(HttpStatus.BAD_REQUEST);
        allError.setTimeStamp(LocalDateTime.now());
        allError.setMessage("Validation Error");
        allError.setDetails(ex.getConstraintViolations().toString());
        return new ResponseEntity<>(allError, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public static ResponseEntity<AllErrorDetails> exceptionHandler3(Exception blogEx, WebRequest wr){
//        System.out.println(blogEx.getClass()+"hello b----------------------------------------------");
//        AllErrorDetails error = new AllErrorDetails();
//        error.setTimeStamp(LocalDateTime.now());
//        error.setDetails(wr.getDescription(false));
//        error.setHttpStatus(HttpStatus.BAD_REQUEST);
//        error.setMessage(blogEx.getMessage());
//
//        return new ResponseEntity<AllErrorDetails>(error, HttpStatus.BAD_REQUEST);
//    }

}
