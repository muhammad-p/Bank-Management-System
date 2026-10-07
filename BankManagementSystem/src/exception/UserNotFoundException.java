package exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException()
    {
        System.out.println("User not registered. Please recheck username and try again.");
    }
}
