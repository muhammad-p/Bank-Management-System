package view;

import service.*;
import exception.*;
import model.UserAccount;

import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;

public class AuthWindow {
    JFrame frame;
    JPanel loginPanel, registerPanel;
    JButton loginButton, registerButton;
    JTextField usernameField,regUsernameField, confirmPasswordField;
    JPasswordField passwordField, regPasswordField;


    public AuthWindow(AuthService auth) {
        frame = new JFrame("Net-Banking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        loginPanel = new JPanel(new GridLayout(8, 2, 10, 30));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));


        loginPanel.add(new JLabel("               New user? Register here:"));
        registerButton = new JButton("Register");
        loginPanel.add(registerButton);

        loginPanel.add(new JLabel(""));
        loginPanel.add(new JLabel(""));

        JLabel l = new JLabel("                         USER LOG-IN");
        loginPanel.add(l);
        l.setFont(new Font("Arial", Font.PLAIN, 24));

        loginPanel.add(new JLabel(""));

        loginPanel.add(new JLabel("                            Username:"));
        usernameField = new JTextField();
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("                            Password:"));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);

        loginPanel.add(new JLabel(""));
        loginPanel.add(new JLabel(""));
        loginPanel.add(new JLabel(""));
        loginButton = new JButton("Login");
        loginPanel.add(loginButton);

        loginPanel.add(new JLabel(""));
        loginPanel.add(new JLabel(""));

        frame.add(loginPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        loginPanel.setVisible(true);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {

                UserAccount user=auth.loginUser(username, password);

                JOptionPane.showMessageDialog(frame, "Login Successful. Redirecting to dashboard... ");

                frame.dispose();
                new DashboardWindow(user,auth);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            loginPanel.setVisible(false);

            registerPanel = new JPanel(new GridLayout(8, 2, 10, 30));
            registerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

   
            registerPanel.add(new JLabel("               Already a user? Login here:"));
            loginButton = new JButton("Login");
            registerPanel.add(loginButton);

            registerPanel.add(new JLabel(""));
            registerPanel.add(new JLabel(""));

            JLabel ll = new JLabel("             USER REGISTRATION");
            registerPanel.add(ll);
            ll.setFont(new Font("Arial", Font.PLAIN, 24));
            registerPanel.add(new JLabel(""));

            registerPanel.add(new JLabel("                            Username:"));
            regUsernameField = new JTextField();
            registerPanel.add(regUsernameField);

            registerPanel.add(new JLabel("                            Password:"));
            regPasswordField = new JPasswordField();
            registerPanel.add(regPasswordField);

            registerPanel.add(new JLabel("                            Confirm Password:"));
            confirmPasswordField = new JTextField();
            registerPanel.add(confirmPasswordField);

            registerPanel.add(new JLabel(""));
            registerPanel.add(new JLabel(""));
            registerPanel.add(new JLabel(""));
            registerButton = new JButton("Register");
            registerPanel.add(registerButton);

            frame.add(registerPanel);
            frame.revalidate();
            frame.repaint();
            registerPanel.setVisible(true);

            loginButton.addActionListener(e1 -> {
                registerPanel.setVisible(false);
                frame.revalidate();
                frame.repaint();
                loginPanel.setVisible(true);

            });

            registerButton.addActionListener(e2 -> {
                String username = regUsernameField.getText();
                char[] pass = regPasswordField.getPassword();
                String password = new String(pass);
                String confirmPassword = confirmPasswordField.getText();

                try {
                    if (!password.equals(confirmPassword)) {
                        throw new PasswordsDoNotMatchException("Passwords do not match.");
                    }

                    UserAccount newAccount = auth.registerUser(username, password);

                    JOptionPane.showMessageDialog(frame,
                            "Successfully registered. You can now log-in.\nUsername is: " + username
                                    + "\nAccount number: " + newAccount.getAccountNumber()
                                    + "\n\nRedirecting to log-in pane...");
                    registerPanel.setVisible(false);
                    frame.revalidate();
                    frame.repaint();
                    loginPanel.setVisible(true);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Registration Failed",
                            JOptionPane.ERROR_MESSAGE);
                }

            });

        });

    }

}
