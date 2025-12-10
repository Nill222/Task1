package com.project.my.factory.impl;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.factory.UserFactory;
import com.project.my.factory.UserIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserFactoryImpl implements UserFactory {

    private static final Logger logger = LogManager.getLogger();
    private final UserIdGenerator userIdGenerator;

    public UserFactoryImpl(UserIdGenerator userIdGenerator) {
        this.userIdGenerator = userIdGenerator;
    }

    @Override
    public User createUser(int[] values) throws UserException{
        if(values.length < 1){
            logger.error("Failed to create user: array is null or empty");
            throw new UserException("Array is null or empty");
        }
        int id = userIdGenerator.generateUserId();
        logger.info("User successfully created with id={} and values={}", id, values);
        return new User(id, values);
    }
}
