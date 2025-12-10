package com.project.my.warehouse.impl;

import com.project.my.dto.UserDto;
import com.project.my.warehouse.UserWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class UserWarehouseImpl implements UserWarehouse {
    private static final Logger logger = LogManager.getLogger();
    private final Map<Integer, UserDto> store = new HashMap<>();

    private UserWarehouseImpl() {}

    public static class Holder {
        public static final UserWarehouseImpl HOLDER_INSTANCE = new UserWarehouseImpl();
    }

    public static UserWarehouseImpl getInstance() {
        return UserWarehouseImpl.Holder.HOLDER_INSTANCE;
    }

    @Override
    public void put(Integer userId, UserDto userDto) {
        store.put(userId, userDto);
        logger.info("Warehouse updated statistics for array ID: {}", userId);
    }
}
