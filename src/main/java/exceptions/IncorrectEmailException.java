package exceptions;

public class IncorrectEmailException extends Exception{
    public IncorrectEmailException(String message) {
        super(message);
    }
}
