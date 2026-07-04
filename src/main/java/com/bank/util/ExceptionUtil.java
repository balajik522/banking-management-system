package com.bank.util;

public class ExceptionUtil {

    private ExceptionUtil() {
    }

    public static void printSuccess(String message) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("SUCCESS");
        System.out.println("========================================");
        System.out.println(message);
        System.out.println("========================================");
    }

    public static void printError(String message) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("ERROR");
        System.out.println("========================================");
        System.out.println(message);
        System.out.println("========================================");
    }

    public static void printInfo(String message) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("INFO");
        System.out.println("========================================");
        System.out.println(message);
        System.out.println("========================================");
    }

}