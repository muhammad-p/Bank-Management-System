package service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


import exception.*;
import model.UserAccount;

public class AuthService {

    final String csvFile = "data/users.csv";
    final Map<String, UserAccount> userCache = new HashMap<>();

    public Map<String, UserAccount> loadCSV() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        String line;

        while ((line = br.readLine()) != null) {
            if (line.trim().equals("")) //bug
                continue;

            String[] data = line.split(",");
            if (data.length == 4) {
                UserAccount account = new UserAccount(data[0], data[1], Double.parseDouble(data[2]), Long.parseLong(data[3]));

                userCache.put(account.getUsername(), account);
            }
        }
        br.close();

        return userCache;

    }

    public void updateCSV() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile));
        for (UserAccount account : userCache.values()) {
            bw.write(account.toCSVrow());
            bw.newLine();
        }
        bw.close();

    }


    public UserAccount registerUser(String username, String password) throws RequiredFieldsEmptyException, UserAlreadyExistsException, IOException {

        if(username.isEmpty() || password.isEmpty() )
            throw new RequiredFieldsEmptyException("Please fill in all fields.");

        if(userCache.containsKey(username))
            throw new UserAlreadyExistsException("This username already exists. Please try again with a different one.");
        boolean isDuplicate=false;
        long newAccountNumber;
        do {
            newAccountNumber = 1_000_000_000L + (long)(Math.random() * 9_000_000_000L); 
            for(UserAccount existingUser: userCache.values() )
            {
                if(existingUser.getAccountNumber()==newAccountNumber)
                {
                    isDuplicate=true;
                    break;
                }
            }
            
        } while (isDuplicate);

        UserAccount newAccount=new UserAccount(username, password, 0, newAccountNumber);
        userCache.put(username, newAccount);
        updateCSV();
        return newAccount;

    }

    public void loginUser(String enteredUsername, String enteredPassword) throws RequiredFieldsEmptyException,InvalidCredentialsException,UserNotFoundException {
        if(enteredUsername.isEmpty() || enteredPassword.isEmpty())
            throw new RequiredFieldsEmptyException("Please fill in all fields.");
        if(!userCache.containsKey(enteredUsername))
            throw new UserNotFoundException("User is not registered. Please recheck username and try again.");

        UserAccount account=userCache.get(enteredUsername);
        boolean isPasswordValid = account.verifyPassword(enteredPassword);
        if(isPasswordValid==false)
            throw new InvalidCredentialsException("The password is incorrect.");
        

    }
}
