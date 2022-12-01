package al.edu.fti.universitymanagement.base.core.validator.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){
        super(message);
    }
}
