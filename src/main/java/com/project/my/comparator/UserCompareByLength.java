package com.project.my.comparator;

import com.project.my.entity.User;

import java.util.Comparator;

public class UserCompareByLength implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        int length1 = (o1.getValues() != null) ? o1.getValues().length : 0;
        int length2 = (o2.getValues() != null) ? o2.getValues().length : 0;
        return Integer.compare(length1, length2);
    }
}
