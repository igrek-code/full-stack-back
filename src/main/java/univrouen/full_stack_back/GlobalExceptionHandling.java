package univrouen.full_stack_back;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected Map<String, String> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("message", "Body request mal formed !");
//        return errors;
//    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    protected Map<String, String> handleRuntimeExceptions(RuntimeException ex) {
//        Map<String, String> errors = new HashMap<>();
//        if(ex.getCause() instanceof  NumberFormatException || ex.getCause() instanceof ConstraintViolationException)
//            errors.put("message", "invalid id");
//        else errors.put("message", ex.getMessage());
//
//        return errors;
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected Map<String, String> handleHttpRequestMethodNotSupportedExceptions(HttpRequestMethodNotSupportedException ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("message", "This request does not exist in the API");
//        return errors;
//    }
}
