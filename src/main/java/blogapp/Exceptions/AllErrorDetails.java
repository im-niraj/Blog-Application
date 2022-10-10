package blogapp.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllErrorDetails {
    private HttpStatus httpStatus;
    private LocalDateTime timeStamp;
    private String message;
    private String details;
}
