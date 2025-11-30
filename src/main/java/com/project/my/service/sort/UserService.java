package com.project.my.service.sort;

import com.project.my.exception.UserException;

public interface UserService {
    int findMinValue(int[] values) throws UserException;

    int findMaxValue(int[] values) throws UserException;

    int sumValues(int[] values) throws UserException;
}
