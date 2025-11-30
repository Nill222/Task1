package com.project.my.service.reader;

import com.project.my.entity.User;
import com.project.my.exception.UserException;

import java.util.List;

public interface UserReaderService {

    List<String> readLines(String path) throws UserException;

    List<User> readUsers(String path) throws UserException;
}
