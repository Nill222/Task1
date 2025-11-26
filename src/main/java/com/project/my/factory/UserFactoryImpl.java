package com.project.my.factory;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserFactoryImpl implements UserFactory {

    private static final Logger logger = LogManager.getLogger(UserFactoryImpl.class);

    @Override
    public User createUser(Integer id, int salary, int age) {
        if(id == null){
            logger.error("Не удалось создать пользователя — id = null");
            throw new UserException("Поля не могут быть пустыми");
        }
        if(id <0 || age <=0 || salary <=0){
            logger.warn("Данные id='{}', salary='{}', age='{}' отрицательны", id, salary, age);
            throw new UserException("данные не могут быть отрицательным");
        }
        logger.debug("Пользователь создан с параметрами id='{}', salary='{}', age='{}'", id, salary, age);
        return new User(id, salary, age);
    }
}
