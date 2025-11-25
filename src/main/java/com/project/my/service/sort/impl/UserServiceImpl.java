package com.project.my.service.sort.impl;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.service.sort.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public int findYoungestAge(User[] users) throws UserException {
        if (users == null || users.length == 0) {
            throw new UserException("Массив пользователей пустой");
        }

        int min = users[0].getAge();
        for (int i = 1; i < users.length; i++) {
            if (users[i].getAge() < min) {
                min = users[i].getAge();
            }
        }
        return min;
    }

    @Override
    public int findMaxSalary(User[] users) throws UserException {
        if (users == null || users.length == 0) {
            throw new UserException("Массив пользователей пустой");
        }

        int max = users[0].getSalary();
        for (int i = 1; i < users.length; i++) {
            if (users[i].getSalary() > max) {
                max = users[i].getSalary();
            }
        }
        return max;
    }

    @Override
    public int sumAge(User[] users) throws UserException {
        if (users == null || users.length == 0) {
            throw new UserException("Массив пользователей пустой");
        }

        int sum = 0;
        for (User u : users) {
            sum += u.getAge();
        }
        return sum;
    }

    @Override
    public void sortByAgeBubble(User[] users) throws UserException {
        if (users == null || users.length == 0) {
            throw new UserException("Массив пользователей пустой");
        }

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
    }

    @Override
    public void sortByAgeQuick(User[] users) throws UserException {
        if (users == null || users.length == 0) {
            throw new UserException("Массив пользователей пустой");
        }

        quickSort(users, 0, users.length - 1);
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
