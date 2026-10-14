package service;

import view.AuthWindow;


public class Main {
    public static void main(String[] args) throws Exception{
        try {
            com.formdev.flatlaf.FlatDarkLaf.setup(); // or new com.formdev.flatlaf.FlatDarkLaf()
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
        AuthService auth=new AuthService(); 
        auth.loadCSV();
        new AuthWindow(auth);
    }
}
