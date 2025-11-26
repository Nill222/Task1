package com.project.my.service;

import com.project.my.entity.User;
import com.project.my.factory.UserFactoryImpl;
import com.project.my.service.reader.impl.UserReaderServiceImpl;
import com.project.my.validation.UserValidatorImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserReaderServiceImplIntegrationTest {

    private Path tempFile;
    private UserReaderServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = Files.createTempFile("users", ".txt");

        UserValidatorImpl validator = new UserValidatorImpl();
        UserFactoryImpl factory = new UserFactoryImpl();

        service = new UserReaderServiceImpl(validator, factory);
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void readLines_validFile_returnsAllLines() throws Exception {
        Files.writeString(tempFile, "1;1000;25\n2;1500;30");

        List<String> lines = service.readLines(tempFile.toString());

        assertEquals(2, lines.size());
        assertEquals("1;1000;25", lines.get(0));
        assertEquals("2;1500;30", lines.get(1));
    }

    @Test
    void readUsers_validAndInvalidLines_createsOnlyValidUsers() throws Exception {
        Files.writeString(tempFile, "1;1000;25\ninvalid;line\n3;1500;35");

        List<User> users = service.readUsers(tempFile.toString());

        assertEquals(2, users.size());
        assertEquals(1, users.get(0).getId());
        assertEquals(3, users.get(1).getId());
    }

    @Test
    void readUsers_emptyFile_returnsEmptyList() throws Exception {
        Files.writeString(tempFile, "");

        List<User> users = service.readUsers(tempFile.toString());

        assertTrue(users.isEmpty());
    }

    @Test
    void readUsers_allInvalidLines_returnsEmptyList() throws Exception {
        Files.writeString(tempFile, "bad;data\n;;;");

        List<User> users = service.readUsers(tempFile.toString());

        assertTrue(users.isEmpty());
    }
}