package com.project.my.factory;

import com.project.my.entity.User;
import com.project.my.exception.UserException;

public class UserFactoryImpl implements UserFactory {
    @Override
    public User createUser(Integer id, int salary, int age) {
        if(id == null || salary == 0 || age == 0){
            throw new UserException("Поля не могут быть пустыми");
        }
        if(id <0 || age <0 || salary <0){
            throw new UserException("данные не могут быть отрицательным");
        }
        return new User(id, salary, age);
    }
}
