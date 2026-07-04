package com.bank.main;

import com.bank.menu.MainMenu;

public class BankApplication {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("            BANKING MANAGEMENT SYSTEM");
        System.out.println("==============================================================");
        System.out.println("        Developed Using Java + JDBC + MySQL");
        System.out.println("==============================================================");
        System.out.println();

        MainMenu mainMenu = new MainMenu();
        mainMenu.start();
    }
}