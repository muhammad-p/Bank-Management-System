
package view;

import service.*;
import exception.*;
import model.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthWindow {
    JFrame frame;
    JPanel loginPanel, registerPanel, contentPanel;
    CardLayout cardLayout;
    JButton loginButton, registerButton;
    JTextField usernameField, regUsernameField, confirmPasswordField;
    JPasswordField passwordField, regPasswordField;
    JLabel loginClickableLabel, registerClickableLabel;
    Font font = new Font("Arial", Font.PLAIN, 15);

    public AuthWindow(AuthService auth) {
        frame = new JFrame("User Authentication and Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setResizable(false);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        loginPanel = new JPanel(new GridLayout(11, 1, 0, 0));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 70, 30, 70));

        JLabel l = new JLabel("Sign in to your account:", SwingConstants.LEFT);
        loginPanel.add(l);
        l.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(font);
        loginPanel.add(usernameLabel);
        usernameField = new JTextField();
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font);
        loginPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);

        loginPanel.add(new JLabel(""));
        loginButton = new JButton("Sign In");
        loginButton.setFocusPainted(false);
        loginPanel.add(loginButton);

        loginPanel.add(new JLabel(""));

        JPanel registerFlowLPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel newUserLabel = new JLabel("New user?");
        newUserLabel.setFont(font);
        registerClickableLabel = new JLabel("Register here");
        registerClickableLabel.setFont(font);
        registerClickableLabel.setForeground(Color.YELLOW);
        registerClickableLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerFlowLPanel.add(newUserLabel);
        registerFlowLPanel.add(registerClickableLabel);
        loginPanel.add(registerFlowLPanel);

        contentPanel.add(loginPanel, "login");
        frame.add(contentPanel);
        cardLayout.show(contentPanel, "login");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        loginPanel.setVisible(true);

        loginClickableLabel = new JLabel("Sign-In");//early instantiation to avoid null ptr exception by action listeners

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {

                UserAccount user = auth.loginUser(username, password);
                frame.dispose();
                new DashboardWindow(user, auth);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerClickableLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerPanel = new JPanel(new GridLayout(11, 1, 0, 0));
                registerPanel.setBorder(BorderFactory.createEmptyBorder(30, 70, 30, 70));


                JLabel ll = new JLabel("Create your account:");
                registerPanel.add(ll);
                ll.setFont(new Font("Arial", Font.PLAIN, 24));

                JLabel regUsernameLabel = new JLabel("Username:");
                regUsernameLabel.setFont(font);
                registerPanel.add(regUsernameLabel);
                regUsernameField = new JTextField();
                registerPanel.add(regUsernameField);

                JLabel regPasswordLabel = new JLabel("Password:");
                regPasswordLabel.setFont(font);
                registerPanel.add(regPasswordLabel);
                regPasswordField = new JPasswordField();
                registerPanel.add(regPasswordField);

                JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
                confirmPasswordLabel.setFont(font);
                registerPanel.add(confirmPasswordLabel);
                confirmPasswordField = new JTextField();
                registerPanel.add(confirmPasswordField);

                registerPanel.add(new JLabel(""));
                registerButton = new JButton("Register");
                registerPanel.add(registerButton);

                registerPanel.add(new JLabel(""));
                JPanel loginFlowLPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
                JLabel loginLabel = new JLabel("Already have an account?");
                loginLabel.setFont(font);
                // loginClickableLabel = new JLabel("Sign-In");//already initialized inside the constructor
                loginClickableLabel.setFont(font);
                loginClickableLabel.setForeground(Color.YELLOW);
                loginClickableLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                loginFlowLPanel.add(loginLabel);
                loginFlowLPanel.add(loginClickableLabel);
                registerPanel.add(loginFlowLPanel);



                contentPanel.add(registerPanel, "register");
                cardLayout.show(contentPanel, "register");
                frame.revalidate();
                frame.repaint();

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
                        cardLayout.show(contentPanel, "login");
                        frame.revalidate();
                        frame.repaint();
        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Registration Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
        
                });

            }

        });

        loginClickableLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                cardLayout.show(contentPanel, "login");
            }
        });

        

    }

}
