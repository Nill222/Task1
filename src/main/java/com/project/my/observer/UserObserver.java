package com.project.my.observer;

import com.project.my.entity.User;

public interface UserObserver {
    void onUserUpdate(User user);
}
