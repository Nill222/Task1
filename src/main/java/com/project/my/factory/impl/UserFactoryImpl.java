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
            logger.error("Не удалось создать пользователя");
            throw new UserException("Массив не может быть пустыми");
        }
        int id = userIdGenerator.generateUserId();
        logger.debug("Пользователь создан");
        return new User(id, values);
    }
}
