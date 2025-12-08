package com.project.my.service.impl;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();

    private boolean isUserExist(User[] user) {
        return user == null || user.length == 0;
    }

    @Override
    public int findMinValue(int[] values) throws UserException {
        logger.info("findMinValue");
        int min = values[0];

        for (int value : values) {
            if (value < min) {
                min = value;
            }
        }
        logger.info("findMinValue result: {}", min);
        return min;
    }

    @Override
    public int findMaxValue(int[] values) throws UserException {
        logger.info("findMaxValue");
        int max = values[0];

        for (int value : values) {
            if (value > max) {
                max = value;
            }
        }
        logger.info("findMaxValue result: {}", max);
        return max;
    }

    @Override
    public int sumValues(int[] values) throws UserException {
        logger.info("sumValues");
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        logger.info("sumValues result: {}", sum);
        return sum;
    }
}
