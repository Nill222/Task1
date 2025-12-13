package com.project.my.specification.impl;

import com.project.my.entity.User;
import com.project.my.service.UserService;
import com.project.my.specification.UserSpecification;

public class UserSumSpecification implements UserSpecification {
    private final int limit;
    private final UserService service;

    public UserSumSpecification(int limit, UserService service) {
        this.limit = limit;
        this.service = service;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        int sum = service.sumValues(user.getValues());
        return sum >= limit;
    }
}
