package service;

import view.AuthWindow;

public class Main {
    public static void main(String[] args) throws Exception{
        AuthService auth=new AuthService(); 
        auth.loadCSV();
        new AuthWindow(auth);
    }
}
