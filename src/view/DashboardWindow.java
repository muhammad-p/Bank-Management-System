package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import model.UserAccount;
import service.AuthService;
import service.DashboardService;

public class DashboardWindow {
    JFrame frame;
    JPanel mainDashboardPanel, northPanel, southPanel, westPanel, centerPanel, insightsPanel,withdrawPanel, depositPanel, transferPanel;
    JButton withdrawPanelButton, withdrawButton, depositPanelButton, depositButton, transferPanelButton, transferButton,
            logOutButton, backButton;
    JLabel balanceLabel,welcomeMessage,usernameLabel, accNoLabel,balLabel,userCountLabel,withdrawLabel,depositLabel,transferAmountLabel,transferToLabel;
    JTextField withdrawAmount,depositAmount, transferAmount,transferToUser;
    CardLayout cardLayout;

    public DashboardWindow(UserAccount user, AuthService auth) {

        DashboardService dashboardService = new DashboardService(user, auth);

        frame = new JFrame("Dashboard");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainDashboardPanel = new JPanel(new BorderLayout(15, 15));
        mainDashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 25, 30));
        Font font = new Font("Arial", Font.PLAIN, 16);

        // north component
        balanceLabel = new JLabel("            Balance: " + user.getBalance() + " $ (USD)");
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 3));
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(95,42));
        northPanel.add(backButton);
        backButton.setVisible(false);
        welcomeMessage = new JLabel("LOOK WHO's BACK... WELCOME " + user.getUsername() + "!");
        welcomeMessage.setFont(new Font("Arial", Font.PLAIN, 25));
        northPanel.add(welcomeMessage);
        balanceLabel.setVisible(false);
        northPanel.add(balanceLabel);

        mainDashboardPanel.add(northPanel, BorderLayout.NORTH);

        // west component (sidebar)
        westPanel = new JPanel(new GridLayout(8, 1, 0, 5));
        withdrawPanelButton = new JButton("Withdraw");
        depositPanelButton = new JButton("Deposit");
        transferPanelButton = new JButton("Transfer");
        westPanel.add(new JLabel(""));
        westPanel.add(new JLabel("Select Service:"));
        westPanel.add(withdrawPanelButton);
        westPanel.add(transferPanelButton);
        westPanel.add(depositPanelButton);
        westPanel.setVisible(true);
        mainDashboardPanel.add(westPanel, BorderLayout.WEST);

        // south component

        southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logOutButton = new JButton("Log-out");
        logOutButton.setPreferredSize(new Dimension(125, 50));
        southPanel.add(logOutButton);

        southPanel.setVisible(true);
        mainDashboardPanel.add(southPanel, BorderLayout.SOUTH);

        // center Component
        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);

        insightsPanel = new JPanel(new GridLayout(10, 1));

        insightsPanel.add(new JLabel(""));
        insightsPanel.add(new JLabel(""));
        usernameLabel = new JLabel("               Username: " + user.getUsername());
        usernameLabel.setFont(font);
        insightsPanel.add(usernameLabel);

        accNoLabel = new JLabel("               Account Number: " + user.getAccountNumber());
        accNoLabel.setFont(font);
        insightsPanel.add(accNoLabel);

        balLabel=new JLabel("               Balance: "+user.getBalance());
        balLabel.setFont(font);
        insightsPanel.add(balLabel);


        insightsPanel.add(new JLabel(""));
        insightsPanel.add(new JLabel(""));
        
        userCountLabel = new JLabel("               Number of users registered on the self-service facility: " + UserAccount.userCount);
        userCountLabel.setFont(font);
        insightsPanel.add(userCountLabel);

        centerPanel.add(insightsPanel, "INSIGHTS_PANEL");
        centerPanel.setVisible(true);

        // withdraw - card panel

        withdrawPanel = new JPanel(new GridLayout(0, 2));
        withdrawLabel = new JLabel("                 Enter amount to withdraw: ");
        withdrawLabel.setFont(font);
        withdrawAmount = new JTextField();
        withdrawButton = new JButton("Confirm");

        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));

        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));

        withdrawPanel.add(withdrawLabel);
        withdrawPanel.add(withdrawAmount);
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(withdrawButton);
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));

        withdrawPanel.setVisible(true);
        centerPanel.add(withdrawPanel, "WITHDRAW_PANEL");

        // deposit- card Panel
        depositPanel = new JPanel(new GridLayout(0, 2));

        depositLabel = new JLabel("                  Enter amount to deposit:");
        depositLabel.setFont(font);
        depositAmount = new JTextField();
        depositButton = new JButton("Confirm");
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(depositLabel);
        depositPanel.add(depositAmount);
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(depositButton);
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));
        depositPanel.add(new JLabel(""));

        depositPanel.setVisible(true);
        centerPanel.add(depositPanel, "DEPOSIT_PANEL");

        // transfer card panel
        transferPanel = new JPanel(new GridLayout(0, 2));

        transferAmountLabel = new JLabel("                  Enter amount to transfer:");
        transferToLabel = new JLabel("                  Enter username of the receiver:");
        transferAmountLabel.setFont(font);
        transferToLabel.setFont(font);
        transferToUser = new JTextField();
        transferAmount = new JTextField();
        transferButton = new JButton("Confirm");

        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(transferAmountLabel);
        transferPanel.add(transferAmount);
        transferPanel.add(transferToLabel);
        transferPanel.add(transferToUser);
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(transferButton);
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));
        transferPanel.add(new JLabel(""));

        transferPanel.setVisible(true);
        centerPanel.add(transferPanel, "TRANSFER_PANEL");

        mainDashboardPanel.add(centerPanel, BorderLayout.CENTER);

        // Action Listeners
        logOutButton.addActionListener(e -> {
            int x = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log-out?", "Log-out user", 0, 2);
            if (x == 0) {
                frame.dispose();
                new AuthWindow(auth);
            }
        });
        withdrawPanelButton.addActionListener(e -> {
            welcomeMessage.setVisible(false);
            logOutButton.setVisible(false);
            backButton.setVisible(true);
            balanceLabel.setVisible(true);
            cardLayout.show(centerPanel, "WITHDRAW_PANEL");
        });
        depositPanelButton.addActionListener(e -> {
            welcomeMessage.setVisible(false);
            logOutButton.setVisible(false);
            backButton.setVisible(true);
            balanceLabel.setVisible(true);
            cardLayout.show(centerPanel, "DEPOSIT_PANEL");
        });
        transferPanelButton.addActionListener(e -> {
            welcomeMessage.setVisible(false);
            logOutButton.setVisible(false);
            backButton.setVisible(true);
            balanceLabel.setVisible(true);
            cardLayout.show(centerPanel, "TRANSFER_PANEL");
        });
        backButton.addActionListener(e -> {
            welcomeMessage.setVisible(true);
            logOutButton.setVisible(true);

            backButton.setVisible(false);
            balanceLabel.setVisible(false);
            balLabel.setText("               Balance: "+user.getBalance());
            cardLayout.show(centerPanel, "INSIGHTS_PANEL");
        });
        withdrawButton.addActionListener(e -> {
            try {
                String amount = withdrawAmount.getText();
                dashboardService.withdraw(amount);
                balanceLabel.setText("            Balance: " + user.getBalance() + " $ (USD)");
                withdrawAmount.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(centerPanel, "Enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(centerPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        depositButton.addActionListener(e -> {
            try {
                String amount = depositAmount.getText();
                dashboardService.deposit(amount);
                balanceLabel.setText("            Balance: " + user.getBalance() + " $ (USD)");
                depositAmount.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(centerPanel, "Enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(centerPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        transferButton.addActionListener(e -> {
            try {
                String amount = transferAmount.getText();
                String transferTo = transferToUser.getText();
                dashboardService.transfer(transferTo, amount);
                balanceLabel.setText("            Balance: " + user.getBalance() + " $ (USD)");
                transferAmount.setText("");
                transferToUser.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(centerPanel, "Enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(centerPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainDashboardPanel.setVisible(true);
        frame.add(mainDashboardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}