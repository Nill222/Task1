package com.project.my.service.reader;

import com.project.my.entity.User;

import java.util.List;

public interface UserReaderService {

    // читает все строки файла
    List<String> readLines(String path);

    // создает список пользователей
    List<User> readUsers(String path);
}
