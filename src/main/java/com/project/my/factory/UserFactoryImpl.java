package com.project.my.factory;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserFactoryImpl implements UserFactory {

    private static final Logger logger = LogManager.getLogger();
    private static int counter = 0;

    @Override
    public User createUser(int[] values) throws UserException{
        if(values.length < 1){
            logger.error("Не удалось создать пользователя");
            throw new UserException("Массив не может быть пустыми");
        }
        int id = ++counter;
        logger.debug("Пользователь создан");
        return new User(id, values);
    }
}
