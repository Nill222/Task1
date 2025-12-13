package com.project.my.service;

import com.project.my.entity.User;
import com.project.my.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryImplTest {

    private UserRepositoryImpl repo;

    @BeforeEach
    void setUp() {
        repo = UserRepositoryImpl.getInstance();
        repo.clear();
    }

    @Test
    void addUser_success() {
        User user = new User(1, new int[]{1, 2});
        assertTrue(repo.add(user));
        assertEquals(1, repo.getAll().size());
    }

    @Test
    void addUser_duplicateId_fails() {
        User user1 = new User(1, new int[]{1});
        User user2 = new User(1, new int[]{2});
        repo.add(user1);
        assertFalse(repo.add(user2));
    }

    @Test
    void removeUser_success() {
        User user = new User(2, new int[]{3});
        repo.add(user);
        assertTrue(repo.remove(user));
        assertEquals(0, repo.getAll().size());
    }

    @Test
    void findById_returnsCorrectUser() {
        User user = new User(3, new int[]{5});
        repo.add(user);
        assertEquals(user, repo.findById(3));
    }

    @Test
    void sortedById_returnsSortedList() {
        User u1 = new User(2, new int[]{1});
        User u2 = new User(1, new int[]{1});
        repo.add(u1);
        repo.add(u2);

        List<User> sorted = repo.sorted((a, b) -> Integer.compare(a.getId(), b.getId()));
        assertEquals(1, sorted.get(0).getId());
    }
}
