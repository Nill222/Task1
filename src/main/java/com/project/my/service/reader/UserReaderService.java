package com.project.my.service.reader;

import com.project.my.entity.User;

import java.util.List;

public interface UserReaderService {

    List<String> readLines(String path);

    List<User> readUsers(String path);
}
