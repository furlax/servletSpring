package ch.heg.examen.todolistapp.Exception;

public class AccountNotExistException extends Exception{
    public AccountNotExistException(String message) {
        super(message);
    }

    public AccountNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
