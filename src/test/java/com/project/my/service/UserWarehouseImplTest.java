package com.project.my.service;

import com.project.my.dto.UserDto;
import com.project.my.warehouse.impl.UserWarehouseImpl;
import org.junit.jupiter.api.Test;

public class UserWarehouseImplTest {

    @Test
    void putStoresDto() {
        UserWarehouseImpl warehouse = UserWarehouseImpl.getInstance();
        UserDto dto = new UserDto(1, 1, 5, 3, 9);

        warehouse.put(1, dto);
    }
}
