package com.project.my.comparator;

import com.project.my.entity.User;

import java.util.Comparator;

public class UserCompareByFirstElement implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        int first1 = (o1.getValues() != null && o1.getValues().length > 0) ? o1.getValues()[0] :0;
        int first2 = (o2.getValues() != null && o2.getValues().length > 0 ? o2.getValues()[0] :0);
        return Integer.compare(first1, first2);
    }
}
