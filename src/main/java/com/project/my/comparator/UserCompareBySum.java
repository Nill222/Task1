package com.project.my.comparator;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.service.UserService;

import java.util.Comparator;

public class UserCompareBySum implements Comparator<User> {

    private final UserService userService;

    public UserCompareBySum(UserService userService) {
        this.userService = userService;
    }

    @Override
    public int compare(User o1, User o2) {
        try {
            int sum1 = userService.sumValues(o1.getValues());
            int sum2 = userService.sumValues(o2.getValues());
            return Integer.compare(sum1, sum2);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
    }
}
