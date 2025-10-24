package amintabite.U5_W3_D5.Exceptions;

import java.util.List;

public class ValidationsException extends RuntimeException {
    public ValidationsException(List<String> message) {
        super(message);
    }
}
