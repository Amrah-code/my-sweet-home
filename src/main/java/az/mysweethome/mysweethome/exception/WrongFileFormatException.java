package az.mysweethome.mysweethome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Uploading file should be in jpeg or png format !")
public class WrongFileFormatException extends RuntimeException{
    public WrongFileFormatException() {
        super("Uploading file should be in jpeg or png format !");
    }
}
