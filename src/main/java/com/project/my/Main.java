package com.project.my;

import com.project.my.config.Config;
import com.project.my.entity.User;
import com.project.my.factory.UserFactory;
import com.project.my.factory.UserFactoryImpl;
import com.project.my.service.reader.UserReaderService;
import com.project.my.service.reader.impl.UserReaderServiceImpl;
import com.project.my.service.sort.UserService;
import com.project.my.service.sort.impl.UserServiceImpl;
import com.project.my.validation.UserValidator;
import com.project.my.validation.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        final Logger logger = LogManager.getLogger(UserServiceImpl.class);

        UserValidator valid = new UserValidatorImpl();

        UserFactory factory = new UserFactoryImpl();

        UserReaderService reader = new UserReaderServiceImpl(valid, factory);

        String path = Config.get("user.file.path");

        List<User> users = reader.readUsers(path);

        logger.debug("Пользователь из файла");
        for (User u : users) {
            logger.debug(u);
        }

        UserService userService = new UserServiceImpl();
        logger.debug("Вызов метода min {}", userService.findYoungestAge(users.toArray(new User[0])));
        logger.debug("Вызов метода max {}", userService.findMaxSalary(users.toArray(new User[0])));
        logger.debug("Вызов метода sum {}", userService.sumAge(users.toArray(new User[0])));
        logger.debug("Вызов метода bubble {}", userService.sortByAgeBubble(users.toArray(new User[0])));
        logger.debug("Вызов метода quick {}", userService.sortByAgeQuick(users.toArray(new User[0])));
    }
}
