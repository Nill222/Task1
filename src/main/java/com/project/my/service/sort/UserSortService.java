package com.project.my.service.sort;

import com.project.my.exception.UserException;

public interface UserSortService {
    void sortByBubble(int[] values) throws UserException;

    void sortByQuick(int[] values) throws UserException;
}
