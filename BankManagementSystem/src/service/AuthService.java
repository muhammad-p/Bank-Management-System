package service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


import exception.UserAlreadyExistsException;
import model.UserAccount;

public class AuthService {

    final String csvFile = "users.csv";
    final Map<String, UserAccount> userCache = new HashMap<>();

    public Map<String, UserAccount> loadCSV() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        String line;

        while ((line = br.readLine()) != null) {
            if (line.trim() == null)
                continue;

            String[] data = line.split(",");
            if (data.length == 4) {
                UserAccount account = new UserAccount(data[0], data[1], Double.parseDouble(data[2]),
                        Long.parseLong(data[3]));

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


    void registerUser()
    {

    }

    void loginUser() {

    }
}
