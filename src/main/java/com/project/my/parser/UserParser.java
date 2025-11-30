package com.project.my.parser;

import com.project.my.exception.UserException;

public interface UserParser {
    int[] parse(String line) throws UserException;
}
