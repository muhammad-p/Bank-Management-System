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
    final String csvFile = "data/users.csv";

    public void withdraw(String amt) throws InsufficientFundsException, InvalidDataEntryException, NumberFormatException,IOException
    {
        double amount = Double.parseDouble(amt);
        if(amount<=0)
            throw new InvalidDataEntryException("Please enter valid data into the field(s).");

        else if(user.getBalance()<amount)
            throw new InsufficientFundsException("Insufficient Balance. ");

        user.addToBalance(-amount);
        auth.updateCSV();
    }
    public void deposit(String amt) throws InsufficientFundsException, InvalidDataEntryException,NumberFormatException,IOException
    {
        double amount=Double.parseDouble(amt);
        if(amount<=0)
            throw new InvalidDataEntryException("Please enter valid data into the field(s).");
        user.addToBalance(amount);
        auth.updateCSV();
    }

}
