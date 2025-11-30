package com.project.my.validation;

public class UserValidatorImpl implements UserValidator{

    @Override
    public boolean canCreateUser(int[] values) {
        return values.length>0;
    }

    @Override
    public boolean isValidLine(String line) {
        if (line == null) {
            return false;
        }
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return false;
        }

        if (!containsOnlyAllowedChars(trimmed)) {
            return false;
        }

        return true;
    }

    private boolean containsOnlyAllowedChars(String line) {
        return line.matches("^[0-9\\s;,\\-]+$");
    }
}
