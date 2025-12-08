package com.project.my.parser.impl;

import com.project.my.parser.UserParser;

public class UserParserImpl implements UserParser {
    private static final String REGEX = "[;,\\-\\s]+";

    @Override
    public int[] parse(String line){
        String[] parts = line.split(REGEX);
            int[] values = new int[parts.length];

            for(int i=0; i<parts.length; i++) {
                values[i] = Integer.parseInt(parts[i]);
            }
            return values;
    }
}
