package az.mysweethome.mysweethome.exhandler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseResource{
    private String message;
    private int code;

    public ErrorResponseResource(String message, int code) {
        this.message = message;
        this.code = code;
    }
}