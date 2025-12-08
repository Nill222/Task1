package com.project.my.validation.impl;

import com.project.my.validation.UserValidator;

public class UserValidatorImpl implements UserValidator {
    private static final String REGEX = "^[0-9\\s;,\\-]+$";

    @Override
    public boolean isValidLine(String line) {
        return line != null
                && !line.isBlank()
                && line.matches(REGEX);
    }

    @Override
    public boolean processLine(String line) {
        if (line == null || line.isBlank()) {
            return false;
        }
        return isValidLine(line);
    }
}
