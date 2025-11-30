package com.project.my.entity.specification;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.service.sort.UserService;
import com.project.my.service.sort.impl.UserServiceImpl;

public class UserSumSpecification implements UserSpecification {
    private final int limit;
    private final UserService service;

    public UserSumSpecification(int limit, UserService service) {
        this.limit = limit;
        this.service = service;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        try {
            int sum = service.sumValues(user.getValues());
            return sum >= limit;
        } catch (UserException e) {
            return false;
        }
    }
}
