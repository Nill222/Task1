package com.project.my.service;

import com.project.my.exception.UserException;

public interface UserSortService {
    void sortByBubble(int[] values);

    void sortByQuick(int[] values);
}
