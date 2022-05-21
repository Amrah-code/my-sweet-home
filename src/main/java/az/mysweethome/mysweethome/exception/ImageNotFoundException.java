package az.mysweethome.mysweethome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Image that you going to delete doesn't exist !")
public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException() {
        super("Image that you going to delete doesn't exist !");
    }
}
