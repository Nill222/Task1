package com.project.my.reader;

import com.project.my.exception.UserException;

import java.util.List;

public interface UserReader {

    List<String> readLines(String path) throws UserException;
}
