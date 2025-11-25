package com.project.my.service;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.service.sort.UserService;
import com.project.my.service.sort.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserService userService;
    private User[] users;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();

        users = new User[]{
                new User(1, 200, 25),
                new User(2, 300, 30),
                new User(3, 100, 20)
        };
    }

    @Test
    void testFindYoungestAge() throws UserException {
        int minAge = userService.findYoungestAge(users);
        assertEquals(20, minAge);
    }

    @Test
    void testFindMaxSalary() throws UserException {
        int maxSalary = userService.findMaxSalary(users);
        assertEquals(300, maxSalary);
    }

    @Test
    void testSumAge() throws UserException {
        int sum = userService.sumAge(users);
        assertEquals(75, sum);
    }

    @Test
    void testSortByAgeBubble() throws UserException {
        userService.sortByAgeBubble(users);

        assertEquals(20, users[0].getAge());
        assertEquals(25, users[1].getAge());
        assertEquals(30, users[2].getAge());
    }

    @Test
    void testSortByAgeQuick() throws UserException {
        userService.sortByAgeQuick(users);

        assertEquals(20, users[0].getAge());
        assertEquals(25, users[1].getAge());
        assertEquals(30, users[2].getAge());
    }

    @Test
    void testEmptyArrayThrowsException() {
        User[] empty = new User[0];

        assertThrows(UserException.class, () -> userService.findYoungestAge(empty));
        assertThrows(UserException.class, () -> userService.sortByAgeBubble(empty));
    }
}

