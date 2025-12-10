package com.project.my.validation.impl;

import com.project.my.validation.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidatorImpl implements UserValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGEX = "^[0-9\\s;,\\-]+$";

    @Override
    public boolean isValidLine(String line) {
        logger.debug("Validating line: '{}'", line);
        return line != null
                && !line.isBlank()
                && line.matches(REGEX);
    }

    @Override
    public boolean processLine(String line) {
        logger.debug("Processing line: '{}'", line);
        if (line == null || line.isBlank()) {
            logger.error("Line is null or blank, cannot process");
            return false;
        }
        return isValidLine(line);
    }
}
