package com.project.my.service;

import com.project.my.exception.UserException;
import com.project.my.service.impl.UserServiceImpl;
import com.project.my.service.impl.UserSortServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserService userService;
    private UserSortService userSortService;

    private int[] values;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        userSortService = new UserSortServiceImpl();
        values = new int[]{1, 2, 3, 4, 5};
    }

    @Test
    void testFindMin() throws UserException {
        int minV = userService.findMinValue(values);
        assertEquals(1, minV);
    }

    @Test
    void testFindMax() throws UserException {
        int maxV = userService.findMaxValue(values);
        assertEquals(5, maxV);
    }

    @Test
    void testSumValues() throws UserException {
        int sum = userService.sumValues(values);
        assertEquals(15, sum);
    }

    @Test
    void testBubbleSort() throws UserException {
        int[] arr = {5, 3, 1, 4, 2};

        userSortService.sortByBubble(arr);

        assertArrayEquals(new int[]{1,2,3,4,5}, arr);
    }

    @Test
    void testQuickSort() throws UserException {
        int[] arr = {5, 3, 1, 4, 2};

        userSortService.sortByQuick(arr);

        assertArrayEquals(new int[]{1,2,3,4,5}, arr);
    }
}

