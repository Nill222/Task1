package com.project.my.factory;

import com.project.my.entity.User;

public interface UserFactory {
    User createUser(Integer id, int name, int age);
}
