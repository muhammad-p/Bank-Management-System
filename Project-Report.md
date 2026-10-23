# Introduction - Bank Management System
This project is a lightweight, object-oriented banking application designed to handle secure user authentication, account management, and core financial transactions (deposits, withdrawals and transfers). Built with a strong focus on software architecture, it uses a flat package structure (model, service, view, exception) to achieve high cohesion and low coupling. User data is loaded into an in-memory HashMap for lightning-fast O(1) lookups and synchronized permanently with a custom CSV persistence layer, all wrapped inside a responsive Java Swing user interface styled with FlatLaf open-source library to deliver a sleek, native experience.

## Purpose
The purpose of this project is to provide a self-service desktop banking portal. It enables users to register new accounts, log in with credentials, inspect real-time account balances, perform fund deposits and withdrawals, and execute account-to-account money transfers with validation.

## Scope
- User Authentication & Onboarding: Account creation, credential verification, automated 10-digit account number generation, and user tracking.
- Financial Transactions: Real-time deposits, withdrawals, and inter-user fund transfers.
- Data Persistence: CSV file reading and atomic row rewriting upon account mutations.
- GUI Customization: Swing components formatted with CardLayout, BorderLayout, GridLayout, FlowLayout, and FlatLaf dark look-and-feel.

# Problem Statement and Objectives

## Problem Statement
Many introductory banking programs rely on temporary memory objects or command-line interfaces, leading to data loss upon termination and plain interfaces. Standard Swing interfaces without external look-and-feel updates often feel outdated. This project addresses these limitations by offering a modular, exception-driven Java GUI application backed by CSV file persistence and styled via FlatLaf.

## Project Objectives
- Architectural Separation: Implement clear package boundaries across model, view, service, and exception packages.
- Interactive GUI: Build an interface using Swing layouts (CardLayout, BorderLayout, GridLayout, FlowLayout) and FlatLaf.
- Validation & Exception Handling: Implement domain-specific custom runtime and checked exceptions for error handling.
- Data Persistence: Ensure account states persist across session restarts using CSV file I/O operations (data/users.csv).

# Software Requirements
### Development & Execution Environment
- Java Development Kit (JDK): JDK 17 or higher (tested on OpenJDK / macOS zsh environment).
- IDE: Visual Studio Code
- Dependencies: flatlaf-3.7.2.jar (added to classpath during compilation and execution).

### Compilation and Execution via terminal
1. Clone to your local machine
2. Move into folder Bank-management System
3. then:
```bash
javac -cp "lib/flatlaf-3.7.2.jar" -d bin src/view/*.java src/model/*.java src/exception/*.java src/service/*.java
```
4. For linux/macOS: 
```bash
java -cp "bin:lib/flatlaf-3.7.2.jar" service.Main
```
4. For Windows:
```bash
java -cp "bin;lib/flatlaf-3.7.2.jar" service.Main

```

# OOP concepts used
1. Encapsulation
Private instance variables (password, balance, accountNumber) in UserAccount prevent direct external modifications. Access and mutations are governed through methods like getBalance(), verifyPassword(), and addToBalance().

2. Custom exception classes inherit directly from Java's Exception base class.

3. Event handling in AuthWindow and DashboardWindow utilizes polymorphic listener overrides. Mouse and action listeners override mouseClicked and actionPerformed behavior:

4. Abstraction
Higher-level components consume simple service calls (auth.loginUser(), dashboardService.transfer()) without directly dealing with parsing logic or file writing routines.

# Implementation

    final String csvFile = "data/users.csv";
    final Map<String, UserAccount> userCache = new HashMap<>();
    public Map<String, UserAccount> loadCSV() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().equals(""))
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

This Java code manages a local user database using a CSV file and an in-memory cache.
Here is what it does in short:
- Reads (loadCSV): Opens data/users.csv, reads it line by line, splits the text by commas, converts the data into UserAccount objects, and stores them in a fast-lookup HashMap (userCache).
- Writes (updateCSV): Loops through all user accounts in the memory cache, converts them back to a CSV string format, and overwrites the data/users.csv file with the updated data.

<br><br>

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
This method handles a money transfer between two users with strict checks:
- Validates: Ensures the recipient exists, is not yourself, and the amount is valid (not empty or negative).
- Checks Funds: Confirms the sender has enough money.
- Moves Money: Subtracts the amount from the sender and adds it to the recipient.
- Saves: Immediately overwrites the CSV file with the new balances.
<br><br>


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
This method handles a secure cash withdrawal from the logged-in user's account:
- Validates: Ensures the input amount is not empty, is a valid number, and is greater than zero.
- Checks Funds: Confirms the user has enough money in their balance to cover the request.
- Deducts Money: Subtracts the amount from the user's balance (-amount).
- Saves: Immediately updates the CSV file with the new balance.

# Conclusion 
This project provides a desktop banking experience implemented in Java. By separating core domain objects, service handlers, GUI containers, and custom exception mechanisms, the project demonstrates scalable design and persistent state management via CSV storage.
## Future Enhancements
- Relational Database Migration: Replace flat CSV storage (users.csv) with JDBC and SQLite/MySQL for transactional query processing.
- Password Security: Upgrade plain-text credential storage to secure salted hash algorithms (e.g., BCrypt or PBKDF2).
- Transaction History Logs: Implement a detailed ledger model tracking individual transaction history with timestamps.
- Statement Generation: Add features to generate downloadable PDF or text summary statements for account activity.

# References

- Oracle America, Inc. Java Platform, Standard Edition Documentation (JDK 17). Java Swing & AWT Documentation.
- FormDev Software GmbH. FlatLaf — Flat Look and Feel for Java Swing. https://www.formdev.com/flatlaf/
- Google Search
