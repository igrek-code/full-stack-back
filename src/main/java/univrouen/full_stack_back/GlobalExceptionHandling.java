package univrouen.full_stack_back;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleInvalidFormatException(InvalidFormatException e) {
        HashMap<String, String> errors = new HashMap<>();
        String field = e.getPath().get(0).getFieldName();
        errors.put(field, "Invalid format");
        return errors;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleNullPointerExceptions(NullPointerException e) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("message", "you must provide a value for id");
        return errors;
    }

//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  protected Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//    Map<String, String> errors = new HashMap<>();
//    ex.getBindingResult()
//        .getAllErrors()
//        .forEach(
//            (error) -> {
//              String fieldName = ((FieldError) error).getField();
//              String errorMessage = error.getDefaultMessage();
//              errors.put(fieldName, errorMessage);
//            });
//    return errors;
//  }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected HashMap<String,String>  handleNumberFormatExceptions(NumberFormatException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("message", "Invalid id supplied");
        return errors;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected HashMap<String,String>  handleIllegalArgumentExceptions(IllegalArgumentException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected HashMap<String,String>  handleEntityNotFoundExceptions(EntityNotFoundException ex) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

//    @ExceptionHandler(RuntimeException.class)
//    protected ResponseEntity<HashMap<String,String>>  handleRuntimeExceptions(RuntimeException ex) {
//        HashMap<String, String> errors = new HashMap<>();
//        errors.put("message", ex.getMessage());
//        if(ex.getMessage().contains("found"))
//            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected HashMap<String, String> handleConstraintViolationExceptions(ConstraintViolationException ex) {
      HashMap<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach((error) -> {
            String fieldName = error.getPropertyPath().toString();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });
      return errors;
    }

  //    @ExceptionHandler(HttpMessageNotReadableException.class)
  //    @ResponseStatus(HttpStatus.BAD_REQUEST)
  //    protected Map<String, String>
  // handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException ex) {
  //        Map<String, String> errors = new HashMap<>();
  //        errors.put("message", "Body request mal formed !");
  //        return errors;
  //    }

  //    @ExceptionHandler(RuntimeException.class)
  //    @ResponseStatus(HttpStatus.NOT_FOUND)
  //    protected Map<String, String> handleRuntimeExceptions(RuntimeException ex) {
  //        Map<String, String> errors = new HashMap<>();
  //        if(ex.getCause() instanceof  NumberFormatException || ex.getCause() instanceof
  // ConstraintViolationException)
  //            errors.put("message", "invalid id");
  //        else errors.put("message", ex.getMessage());
  //
  //        return errors;
  //    }
  //
  //    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  //    @ResponseStatus(HttpStatus.BAD_REQUEST)
  //    protected Map<String, String>
  // handleHttpRequestMethodNotSupportedExceptions(HttpRequestMethodNotSupportedException ex) {
  //        Map<String, String> errors = new HashMap<>();
  //        errors.put("message", "This request does not exist in the API");
  //        return errors;
  //    }
}
