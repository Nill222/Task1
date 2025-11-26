package com.project.my.service.sort.impl;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.service.sort.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;

import java.util.Arrays;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public int findYoungestAge(User[] users) throws UserException {
        if (isUserExist(users)) {
            logger.warn("Невозможно найти минимальный возраст — массив пустой");
            throw new UserException("Массив пользователей пустой");
        }

        logger.debug("Нахождение самого молодого пользователя");
        int min = users[0].getAge();
        for (int i = 1; i < users.length; i++) {
            if (users[i].getAge() < min) {
                min = users[i].getAge();
            }
        }

        logger.info("Самый молодой пользователь '{}'", min );
        return min;
    }

    @Override
    public int findMaxSalary(User[] users) throws UserException {
        if (isUserExist(users)) {
            logger.warn("Невозможно найти максимальную зарплату — массив пустой");
            throw new UserException("Массив пользователей пустой");
        }

        logger.debug("Нахождение самого богатого пользователя");

        int max = users[0].getSalary();
        for (int i = 1; i < users.length; i++) {
            if (users[i].getSalary() > max) {
                max = users[i].getSalary();
            }
        }

        logger.info("Самый богатый пользователь '{}'", max);
        return max;
    }

    @Override
    public int sumAge(User[] users) throws UserException {
        if (isUserExist(users)) {
            logger.warn("Невозможно найти сумму возрастов — массив пустой");
            throw new UserException("Массив пользователей пустой");
        }

        logger.debug("Нахождение суммы возрастов");

        int sum = 0;
        for (User u : users) {
            sum += u.getAge();
        }

        logger.info("Сумма возрастов пользователей '{}'", sum);

        return sum;
    }

    @Override
    public Message sortByAgeBubble(User[] users) throws UserException {
        if (isUserExist(users)) {
            logger.warn("Невозможно выполнить пузырьковую сортировку — массив пустой");
            throw new UserException("Массив пользователей пустой");
        }

        logger.debug("Сортировка пользователей методом пузырьков");

        int n = users.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (users[j].getAge() > users[j + 1].getAge()) {
                    User temp = users[j];
                    users[j] = users[j + 1];
                    users[j + 1] = temp;
                }
            }
        }

        logger.info("Пузырьковая сортировка выполнена");
        logger.debug("Результат: {}", Arrays.toString(users));
        return null;
    }

    @Override
    public Message sortByAgeQuick(User[] users) throws UserException {
        if (users == null || users.length == 0) {
            logger.warn("Невозможно выполнить быструю сортировку — массив пустой");
            throw new UserException("Массив пользователей пустой");
        }

        logger.debug("Сортировка пользователей методом быстрой сортировки");

        quickSort(users, 0, users.length - 1);

        logger.info("Быстрая сортировка завершена");
        logger.debug("Результат быстрой сортировки: {}", Arrays.toString(users));
        return null;
    }

    private boolean isUserExist(User[] user) {
        return user == null || user.length == 0;
    }

    private void quickSort(User[] users, int low, int high) {
        if (low < high) {
            int pi = partition(users, low, high);
            quickSort(users, low, pi - 1);
            quickSort(users, pi + 1, high);
        }
    }

    private int partition(User[] users, int low, int high) {
        int pivot = users[high].getAge();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (users[j].getAge() < pivot) {
                i++;
                User temp = users[i];
                users[i] = users[j];
                users[j] = temp;
            }
        }
        User temp = users[i + 1];
        users[i + 1] = users[high];
        users[high] = temp;
        return i + 1;
    }
}
