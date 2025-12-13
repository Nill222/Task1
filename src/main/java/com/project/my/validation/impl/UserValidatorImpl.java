package com.project.my.validation.impl;

import com.project.my.validation.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidatorImpl implements UserValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String VALID_REGEX = "^[0-9\\s;,\\-]+$";

    @Override
    public boolean isValidLine(String line) {
        logger.debug("Validating line: '{}'", line);
        return line != null
                && !line.isBlank()
                && line.matches(VALID_REGEX);
    }
}
