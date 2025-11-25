package com.project.my.validation;

import com.project.my.entity.User;

public interface UserValidator {
    boolean isValidLine(User user);

    boolean isValidId(Integer id);

    boolean isValidAge(int age);

    boolean isValidSalary(int salary);

    boolean isValidLine(String line);

    boolean isValidArray(User[] users);

    boolean canCreateUser(Integer id, int age, int salary);
}
