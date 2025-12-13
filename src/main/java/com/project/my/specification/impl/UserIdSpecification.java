package com.project.my.specification.impl;

import com.project.my.entity.User;
import com.project.my.specification.UserSpecification;

import java.util.Objects;

public class UserIdSpecification implements UserSpecification {
    private final Integer userId;

    public UserIdSpecification(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        return Objects.equals(user.getId(), userId);
    }
}
