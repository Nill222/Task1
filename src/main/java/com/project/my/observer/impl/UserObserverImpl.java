package com.project.my.observer.impl;

import com.project.my.entity.User;
import com.project.my.dto.UserDto;
import com.project.my.observer.UserObserver;
import com.project.my.service.UserService;
import com.project.my.service.impl.UserServiceImpl;
import com.project.my.warehouse.UserWarehouse;
import com.project.my.warehouse.impl.UserWarehouseImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserObserverImpl implements UserObserver {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = new UserServiceImpl();


    @Override
    public void onUserUpdate(User user) {
        int userId = user.getId();
        int[] values = user.getValues();

        logger.debug("User update triggered for id={} with values length={}", userId, values.length);

        UserWarehouse warehouse = UserWarehouseImpl.getInstance();

        int sum = userService.sumValues(values);
        int min = userService.findMinValue(values);
        int max = userService.findMaxValue(values);
        int avg = userService.sumValues(values) / values.length;

        warehouse.put(userId, new UserDto(userId, min, max, avg, sum));

        logger.info("Warehouse updated for user id={} with stats: min={}, max={}, avg={}, sum={}",
                userId, min, max, avg, sum);
    }
}
