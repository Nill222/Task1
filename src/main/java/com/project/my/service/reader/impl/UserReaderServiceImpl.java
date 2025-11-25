package com.project.my.service.reader.impl;

import com.project.my.entity.User;
import com.project.my.factory.UserFactory;
import com.project.my.service.reader.UserReaderService;
import com.project.my.validation.UserValidator;

import java.util.List;

public class UserReaderServiceImpl implements UserReaderService {

    private final UserValidator validator;
    private final UserFactory factory;

    public UserReaderServiceImpl(UserValidator validator, UserFactory factory) {
            this.validator = validator;
            this.factory = factory;
    }

    @Override
    public List<String> readLines(String path) {
        return List.of();
    }

    @Override
    public List<User> readUsers(String path) {
        return List.of();
    }
}
