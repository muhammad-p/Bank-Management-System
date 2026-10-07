package exception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException()
    {
        System.out.println("This username already exists. Please try again with a different username.");
    }
}
