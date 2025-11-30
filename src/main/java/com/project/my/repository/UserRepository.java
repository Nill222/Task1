package com.project.my.repository;

import com.project.my.entity.User;
import com.project.my.entity.specification.UserSpecification;

import java.util.Comparator;
import java.util.List;

public interface UserRepository {
    boolean add(User user);

    boolean remove(User user);

    boolean removeById(int id);

    List<User> getAll();

    User findById(int id);

    List<User> find(UserSpecification specification);

    void sort(Comparator<User> comparator);

    List<User> sorted(Comparator<User> comparator);

    void clear();
}
