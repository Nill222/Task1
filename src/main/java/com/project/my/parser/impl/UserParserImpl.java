package com.project.my.parser.impl;

import com.project.my.parser.UserParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserParserImpl implements UserParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String SPLIT_REGEX = "[;,\\-\\s]+";

    @Override
    public int[] parse(String line) {
        logger.debug("Attempting to parse line: '{}'", line);
        String[] parts = line.split(SPLIT_REGEX);
            int[] values = new int[parts.length];

            for(int i=0; i<parts.length; i++) {
                values[i] = Integer.parseInt(parts[i]);
            }
            logger.info("Successfully parsed line '{}'. Parsed values count: {}", line, values.length);
            return values;
    }
}
