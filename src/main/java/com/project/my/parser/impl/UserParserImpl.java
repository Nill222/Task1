package com.project.my.parser.impl;

import com.project.my.exception.UserException;
import com.project.my.parser.UserParser;

public class UserParserImpl implements UserParser {
    @Override
    public int[] parse(String line) throws UserException {
        String[] parts = line.split("[;,\\-\\s]+");

        try {
            int[] values = new int[parts.length];

            for(int i=0; i<parts.length; i++) {
                values[i] = Integer.parseInt(parts[i]);
            }
            return values;

        } catch (NumberFormatException e) {
            throw new UserException(e.getMessage());
        }
    }
}
