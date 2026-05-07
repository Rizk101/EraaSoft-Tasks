package Service.impl;

import Service.ValidationService;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean isUserNameValid(String userName) {
        if (userName.isBlank()) {
            return false;
        }

        if (userName.length() < 3) {
            return false;
        }

        if (userName.matches(".*\\d.*")) {
            return false;
        }

        if (!Character.isLetter(userName.charAt(0))) {
            return false;
        }

        if (!Character.isUpperCase(userName.charAt(0))) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isPasswordValid(String password) {
        if (password.isBlank()) {
            return false;
        }
        if (password.length() < 8) {
            return false;
        }


        // must start with a letter
        if (!Character.isLetter(password.charAt(0))) {
            return false;
        }

        // must contain at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // must contain at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // must contain at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // must contain at least one special character
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            return false;
        }

        return true;
    }
}