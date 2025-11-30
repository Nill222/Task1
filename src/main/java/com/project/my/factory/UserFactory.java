package com.project.my.factory;

import com.project.my.entity.User;
import com.project.my.exception.UserException;

public interface UserFactory {
    User createUser(int[] values) throws UserException;
}
