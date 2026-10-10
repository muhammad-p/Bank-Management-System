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

public class DashboardWindow {
    JFrame frame;
    JPanel mainDashboardPanel, northPanel, southPanel, westPanel, centerPanel;
    JButton withdrawPanelButton, withdrawButton, depositPanelButton, depositButton, transferPanelButton, transferButton,
            logOutButton, backButton;

    public DashboardWindow(UserAccount user, AuthService auth) {
        frame = new JFrame("Dashboard");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainDashboardPanel = new JPanel(new BorderLayout(15, 15));
        mainDashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 25, 30));

        // north component
        northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 3));
        backButton=new JButton("<-");
        northPanel.add(backButton);
        backButton.setVisible(false);
        JLabel welcomeMessage = new JLabel("WELCOME BACK, " + user.getUsername() + "!");
        welcomeMessage.setFont(new Font("Arial", Font.PLAIN, 23));
        northPanel.add(welcomeMessage);
        mainDashboardPanel.add(northPanel, BorderLayout.NORTH);

        // west component (sidebar)
        westPanel = new JPanel(new GridLayout(8, 1, 0, 5));
        withdrawPanelButton = new JButton("Withdraw");
        depositPanelButton = new JButton("Deposit");
        transferButton = new JButton("Transfer");
        westPanel.add(new JLabel(""));
        westPanel.add(new JLabel("Select Service:"));
        westPanel.add(withdrawPanelButton);
        westPanel.add(transferButton);
        westPanel.add(depositPanelButton);
        westPanel.setVisible(true);
        mainDashboardPanel.add(westPanel, BorderLayout.WEST);

        // south component

        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(new JLabel("Number of users registered on the net-banking portal: " + UserAccount.userCount
                + "                                                             "));
        logOutButton = new JButton("Log-out");
        logOutButton.setPreferredSize(new Dimension(100, 40));
        southPanel.add(logOutButton);

        southPanel.setVisible(true);
        mainDashboardPanel.add(southPanel, BorderLayout.SOUTH);

        // center Component
        CardLayout cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);

        JPanel insightsPanel = new JPanel(new GridLayout(10, 1));
        Font font = new Font("Arial", Font.PLAIN, 16);

        // centerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        insightsPanel.add(new JLabel(""));
        insightsPanel.add(new JLabel(""));
        JLabel usernameLabel, accNoLabel, balLabel;
        usernameLabel = new JLabel("                  Username: " + user.getUsername());
        usernameLabel.setFont(font);
        insightsPanel.add(usernameLabel);

        accNoLabel = new JLabel("                  Account Number: " + user.getAccountNumber());
        accNoLabel.setFont(font);
        insightsPanel.add(accNoLabel);

        balLabel = new JLabel("                  Balance: " + user.getBalance());
        balLabel.setFont(font);
        insightsPanel.add(balLabel);

        centerPanel.add(insightsPanel, "INSIGHTS_PANEL");
        centerPanel.setVisible(true);

        // withdraw - card panel
        
        JLabel balance = new JLabel("                          Balance: " + user.getBalance());
        balance.setFont(font);
        JPanel withdrawPanel = new JPanel(new GridLayout(0, 2));
        JLabel withdrawLabel=new JLabel("                    Enter amount to withdraw: ");
        withdrawLabel.setFont(font);
        JTextField withdrawAmount=new JTextField();
        withdrawButton = new JButton("Confirm");

        
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(new JLabel(""));
        withdrawPanel.add(balance);
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
        JPanel depositPanel = new JPanel(new GridLayout(0, 2));

        JLabel depositLabel=new JLabel("                     Enter amount to deposit:");
        depositLabel.setFont(font);
        JTextField depositAmount=new JTextField();
        depositButton=new JButton("Confirm");
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
            backButton.setVisible(true);
            cardLayout.show(centerPanel, "WITHDRAW_PANEL");
        });
        depositPanelButton.addActionListener(e -> {
            welcomeMessage.setVisible(false);
            backButton.setVisible(true);
            cardLayout.show(centerPanel, "DEPOSIT_PANEL");
        });
        backButton.addActionListener(e->
            {
                welcomeMessage.setVisible(true);
                backButton.setVisible(false);
                cardLayout.show(centerPanel, "INSIGHTS_PANEL");
            }
        );
        // transferPanelButton.addActionListener(e -> {
        //     cardLayout.show(centerPanel, "TRANSFER_PANEL");
        // });

        mainDashboardPanel.setVisible(true);
        frame.add(mainDashboardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}