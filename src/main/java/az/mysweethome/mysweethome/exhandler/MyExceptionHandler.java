package az.mysweethome.mysweethome.exhandler;

import az.mysweethome.mysweethome.exception.ImageNotFoundException;
import az.mysweethome.mysweethome.exception.WrongFileFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseResource> handleImageNotFoundException(ImageNotFoundException imageNotFoundException){
        ErrorResponseResource ex = new ErrorResponseResource(imageNotFoundException.getMessage(), 404);
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongFileFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseResource> handleWrongFileFormatException(WrongFileFormatException wrongFileFormatException){
        ErrorResponseResource ex = new ErrorResponseResource(wrongFileFormatException.getMessage(), 400);
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}
