package com.project.my.comparator;

import com.project.my.entity.User;
import java.util.Comparator;
import java.util.Optional;

public class UserCompareById implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        int id1 = Optional.ofNullable(o1.getId()).orElse(0);
        int id2 = Optional.ofNullable(o2.getId()).orElse(0);
        return Integer.compare(id1, id2);
    }
}
