package service;

import model.UserAccount;

import java.io.IOException;

import exception.*;

public class DashboardService {
    UserAccount user;
    AuthService auth;
    public DashboardService(UserAccount user, AuthService auth)
    {
        this.user=user;
        this.auth=auth;
    }

    public void withdraw(String amt) throws InsufficientFundsException, InvalidDataEntryException, NumberFormatException,IOException,RequiredFieldsEmptyException
    {
        if(amt.isEmpty())
            throw new RequiredFieldsEmptyException("Enter amount to be transferred.");
        double amount = Double.parseDouble(amt);
        if(amount<=0)
            throw new InvalidDataEntryException("Please enter valid data into the field(s).");

        if(user.getBalance()<amount)
            throw new InsufficientFundsException("Insufficient Balance. ");

        user.addToBalance(-amount);
        auth.updateCSV();
    }
    public void deposit(String amt) throws InsufficientFundsException, InvalidDataEntryException,NumberFormatException,IOException, RequiredFieldsEmptyException
    {
        if(amt.isEmpty())
            throw new RequiredFieldsEmptyException("Enter amount to be transferred.");
        double amount=Double.parseDouble(amt);
        if(amount<=0)
            throw new InvalidDataEntryException("Please enter valid data into the field(s).");
        user.addToBalance(amount);
        auth.updateCSV();
    }

    public void transfer(String transferToUsername, String amt) throws InsufficientFundsException, UserNotFoundException, InvalidDataEntryException, IOException, RequiredFieldsEmptyException, TransferToSelfException
    {
        if(amt.isEmpty())
            throw new RequiredFieldsEmptyException("Enter amount to be transferred.");
        UserAccount transferTo=auth.userCache.get(transferToUsername);
        if(transferTo==null)
            throw new UserNotFoundException("The username you entered is not registered. Recheck the username and try-again.");
        else if(transferTo.equals(user))
            throw new TransferToSelfException("Cannot transfer to self.");

        double amount=Double.parseDouble(amt);
        
        if(user.getBalance()<amount)
            throw new InsufficientFundsException("Insufficient balance.");

        if(amount<=0)
            throw new InvalidDataEntryException("Please enter valid data into the field(s).");

        user.addToBalance(-amount);
        transferTo.addToBalance(amount);
        auth.updateCSV();

    }

}
