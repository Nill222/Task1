package com.project.my.repository.impl;

import com.project.my.entity.User;
import com.project.my.observer.UserObserver;
import com.project.my.repository.UserRepository;
import com.project.my.specification.UserSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger();
    private final List<User> users = new ArrayList<>();
    private final List<UserObserver> observers = new ArrayList<>();

    private UserRepositoryImpl() {}

    public static class Holder {
        public static final UserRepositoryImpl HOLDER_INSTANCE = new UserRepositoryImpl();
    }

    public static UserRepositoryImpl getInstance() {
        return Holder.HOLDER_INSTANCE;
    }

    @Override
    public void addObserver(UserObserver observer) {
        observers.add(observer);
        logger.info("Observer added: {}", observer.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(UserObserver observer) {
        observers.remove(observer);
        logger.info("Observer removed: {}", observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers(User user) {
        for (UserObserver observer : observers) {
            logger.debug("Notifying observer {} for user id={}", observer.getClass().getSimpleName(), user.getId());
            observer.onUserUpdate(user);
        }
    }

    @Override
    public boolean add(User user) {
        if(user == null) {
            logger.warn("user is null");
            return false;
        }
        if(findById(user.getId()) != null) {
            logger.warn("user already exists");
            return false;
        }
        logger.debug("add user");
        users.add(user);
        notifyObservers(user);
        return true;
    }

    @Override
    public boolean remove(User user) {
        if(user == null) {
            logger.warn("Attempted to add null user");
            return false;
        }
        boolean removed = users.remove(user);
        if (removed) {
            logger.info("User removed with id={}", user.getId());
            notifyObservers(user);
        }
        logger.warn("Failed to remove user with id={}", user.getId());
        return removed;
    }

    @Override
    public boolean removeById(int id) {
        if(findById(id) == null) {
            logger.warn("User with id={} not found for removal", id);
            return false;
        }
        boolean removed = users.remove(findById(id));
        if(removed) {
            logger.info("User removed by id={}", id);
            notifyObservers(findById(id));
        }
        return removed;
    }

    @Override
    public List<User> getAll() {
        logger.info("Retrieving all users, total count={}", users.size());
        return new ArrayList<>(users);
    }

    @Override
    public User findById(int id) {
        logger.debug("findById({})", id);
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<User> find(UserSpecification specification) {
        if(specification == null) {
            logger.warn("Specification is null, returning all users");
            return new ArrayList<>(users);
        }
        logger.debug("Searching users by specification {}", specification.getClass().getSimpleName());
        return users.stream().filter(specification::isSatisfiedBy).toList();
    }

    @Override
    public List<User> sorted(Comparator<User> comparator) {
        if (comparator == null) {
            logger.warn("Comparator is null, returning unsorted list");
            return new ArrayList<>(users);
        }
        logger.debug("Sorting users with comparator {}", comparator.getClass().getSimpleName());
        return users.stream()
                .sorted(comparator)
                .toList();
    }
}
