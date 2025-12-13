package com.project.my.specification.impl;

import com.project.my.entity.User;
import com.project.my.service.UserService;
import com.project.my.specification.UserSpecification;

public class UserMaxEqualsSpecification implements UserSpecification {
    private final int value;
    private final UserService service;

    public UserMaxEqualsSpecification(int value, UserService service) {
        this.value = value;
        this.service = service;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        return service.findMaxValue(user.getValues()) == value;
    }
}
