package com.bank.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[6-9][0-9]{9}$");

    public static boolean isValidEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {

        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }

        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidAmount(BigDecimal amount) {

        return amount != null &&
               amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean isValidName(String name) {

        return name != null &&
               name.trim().length() >= 3;
    }

    public static boolean isValidAccountNumber(String accountNumber) {

        return accountNumber != null &&
               accountNumber.trim().length() >= 8;
    }
}