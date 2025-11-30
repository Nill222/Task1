package com.project.my.entity.specification;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.service.sort.UserService;
import com.project.my.service.sort.impl.UserServiceImpl;

public class UserMinSpecification implements UserSpecification {
    private final int min;
    private final UserService service;

    public UserMinSpecification(int min, UserService service) {
        this.min = min;
        this.service = service;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        try {
            return service.findMinValue(user.getValues()) >= min;
        } catch (UserException e) {
            return false;
        }
    }
}
