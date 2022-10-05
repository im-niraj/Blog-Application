package blogapp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobleException {

    @ExceptionHandler(BlogException.class)
    public static ResponseEntity<AllErrorDetails> exceptionHandler(BlogException blogEx, WebRequest wr){
        AllErrorDetails error = new AllErrorDetails();
        error.setTimeStamp(LocalDateTime.now());
        error.setDescription(wr.getDescription(false));
        return new ResponseEntity<AllErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public static ResponseEntity<AllErrorDetails> exceptionHandler2(BlogException blogEx, WebRequest wr){
        AllErrorDetails error = new AllErrorDetails();
        error.setTimeStamp(LocalDateTime.now());
        error.setDescription(wr.getDescription(false));
        return new ResponseEntity<AllErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

}
