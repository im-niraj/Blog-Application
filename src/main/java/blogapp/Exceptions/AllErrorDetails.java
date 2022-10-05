package blogapp.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllErrorDetails {
    private LocalDateTime timeStamp;
    private String message;
    private String description;
}
