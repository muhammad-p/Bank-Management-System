package exception;

public class RequiredFieldsEmptyException extends Exception{
    public RequiredFieldsEmptyException(String message)
    {
        super(message);
    }
}