package com.project.my.service;

import com.project.my.exception.UserException;

public interface UserService {
    int findMinValue(int[] values) throws UserException;

    int findMaxValue(int[] values) throws UserException;

    int sumValues(int[] values) throws UserException;
}
