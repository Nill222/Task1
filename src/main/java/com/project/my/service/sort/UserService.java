package com.project.my.service.sort;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import org.apache.logging.log4j.message.Message;

public interface UserService {
    int findYoungestAge(User[] users) throws UserException;

    int findMaxSalary(User[] users) throws UserException;

    int sumAge(User[] users) throws UserException;

    Message sortByAgeBubble(User[] users) throws UserException;

    Message sortByAgeQuick(User[] users) throws UserException;
}
