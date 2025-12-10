package com.project.my.repository;

import com.project.my.entity.User;
import com.project.my.observer.UserObserver;
import com.project.my.specification.UserSpecification;

import java.util.Comparator;
import java.util.List;

public interface UserRepository {
    void addObserver(UserObserver observer);

    void removeObserver(UserObserver observer);

    void notifyObservers(User user);

    boolean add(User user);

    boolean remove(User user);

    boolean removeById(int id);

    List<User> getAll();

    User findById(int id);

    List<User> find(UserSpecification specification);

    List<User> sorted(Comparator<User> comparator);
}
