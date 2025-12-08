package com.project.my.warehouse.impl;

import com.project.my.entity.User;
import com.project.my.entity.UserDto;
import com.project.my.warehouse.UserWarehouse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserWarehouseImpl implements UserWarehouse {
    private final Map<Integer, UserDto> store = new HashMap<>();

    private UserWarehouseImpl() {}

    public static class Holder {
        public static final UserWarehouseImpl HOLDER_INSTANCE = new UserWarehouseImpl();
    }

    public static UserWarehouseImpl getInstance() {
        return UserWarehouseImpl.Holder.HOLDER_INSTANCE;
    }

//
//    @Override
//    public UserDto getStats(Integer id) {
//        return null;
//    }
//
//    @Override
//    public void remove(Integer id) {
//
//    }

    @Override
    public void onUserUpdate(User user) {
        int[] arr = user.getValues();
        int sum = Arrays.stream(arr).sum();
        int min = Arrays.stream(arr).min().orElse(0);
        int max = Arrays.stream(arr).max().orElse(0);
        int avg = Arrays.stream(arr).sum() / arr.length;

        store.put(user.getId(), new UserDto(user.getId(), max, sum, min, avg));
    }
}
