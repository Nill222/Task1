package com.project.my.service;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.factory.UserIdGenerator;
import com.project.my.factory.impl.UserFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserFactoryImplTest {

    @Test
    void createUser_validArray_success() throws UserException {
        UserIdGenerator generator = () -> 1;
        UserFactoryImpl factory = new UserFactoryImpl(generator);

        User user = factory.createUser(new int[]{1, 2, 3});

        assertNotNull(user);
        assertEquals(1, user.getId());
        assertArrayEquals(new int[]{1, 2, 3}, user.getValues());
    }

    @Test
    void createUser_emptyArray_throwsException() {
        UserIdGenerator generator = () -> 1;
        UserFactoryImpl factory = new UserFactoryImpl(generator);

        assertThrows(UserException.class, () -> factory.createUser(new int[]{}));
    }
}
