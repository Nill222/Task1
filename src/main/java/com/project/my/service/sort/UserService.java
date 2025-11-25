package com.project.my.service.sort;

import com.project.my.entity.User;
import com.project.my.exception.UserException;

public interface UserService {
    int findYoungestAge(User[] users) throws UserException;

    int findMaxSalary(User[] users) throws UserException;

    int sumAge(User[] users) throws UserException;

    void sortByAgeBubble(User[] users) throws UserException;

    void sortByAgeQuick(User[] users) throws UserException;
}
