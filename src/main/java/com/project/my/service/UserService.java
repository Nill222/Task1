package com.project.my.service;

import com.project.my.exception.UserException;

public interface UserService {
    int findMinValue(int[] values);

    int findMaxValue(int[] values);

    int sumValues(int[] values);
}
