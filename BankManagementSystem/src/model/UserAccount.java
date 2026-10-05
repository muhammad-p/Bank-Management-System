package model;

public class UserAccount {
    public final String username;
    private String password;
    private double balance;
    private final long accountNumber;
    public UserAccount(String usr, String pass, double bal, long accNo)
    {
        username=usr; password=pass; balance=bal; accountNumber=accNo;
    }
    public String getUsername()
    {
        return username;
    }
    public double getBalance()
    {
        return balance;
    }
    public long getAccountNumber()
    {
        return accountNumber;
    }
    public boolean verifyPassword(String enteredPassword)
    {
        if(this.password==null || enteredPassword==null)
            return false;
        return this.password.equals(enteredPassword);
    }
    public void setBalance(double bal)
    {
        this.balance=bal;
    }
    
    
    public String toCSVrow()
    {
        return String.format("%s,%s,%.2f,%d",
        this.username,
        this.password,
        this.balance,
        this.accountNumber);
    }

}
