package com.project.my.repository;

import com.project.my.entity.User;
import com.project.my.specification.UserSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger();

    private UserRepositoryImpl() {}

    public static class Holder {
        public static final UserRepositoryImpl HOLDER_INSTANCE = new UserRepositoryImpl();
    }

    public static UserRepositoryImpl getInstance() {
        return Holder.HOLDER_INSTANCE;
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
        return true;
    }

    @Override
    public boolean remove(User user) {
        if(user == null) {
            logger.warn("пользователь пуст");
            return false;
        }
        logger.debug("удаление пользователя");
        return users.remove(user);
    }

    @Override
    public boolean removeById(int id) {
        if(findById(id) == null) {
            logger.warn("пользователь не найден");
            return false;
        }
        logger.debug("удаление пользователя с id = '{}'", id);
        return users.remove(findById(id));
    }

    @Override
    public List<User> getAll() {
        logger.info("получение всех пользователей");
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
            logger.warn("отсутствует спецификация");
            return new ArrayList<>(users);
        }
        logger.debug("поиск по спецификации");
        return users.stream().filter(specification::isSatisfiedBy).toList();
    }

    @Override
    public void sort(Comparator<User> comparator) {
        if (comparator == null) {
            logger.warn("comparator пуст");
            return;
        }
        logger.debug("сортировка методом sort");
        users.sort(comparator);
    }

    @Override
    public List<User> sorted(Comparator<User> comparator) {
        if (comparator == null) {
            logger.warn("comparator пустой");
            return new ArrayList<>(users);
        }
        logger.debug("сортировка методом sorted");
        return users.stream()
                .sorted(comparator)
                .toList();
    }

    @Override
    public void clear() {
        users.clear();
        logger.info("UserRepositoryImpl clear");
    }
}
