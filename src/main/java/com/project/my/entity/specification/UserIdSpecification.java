package com.project.my.entity.specification;

import com.project.my.entity.User;

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
