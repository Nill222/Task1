package com.project.my.warehouse;

import com.project.my.dto.UserDto;

public interface UserWarehouse {
    void put(Integer userId, UserDto userDto);
}
