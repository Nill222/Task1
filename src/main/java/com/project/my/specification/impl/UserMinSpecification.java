package com.project.my.specification.impl;

import com.project.my.entity.User;
import com.project.my.service.UserService;
import com.project.my.specification.UserSpecification;

public class UserMinSpecification implements UserSpecification {
    private final int min;
    private final UserService service;

    public UserMinSpecification(int min, UserService service) {
        this.min = min;
        this.service = service;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        return service.findMinValue(user.getValues()) >= min;
    }
}
