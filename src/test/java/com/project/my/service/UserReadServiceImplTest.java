package com.project.my.service;

import com.project.my.entity.User;
import com.project.my.factory.UserFactoryImpl;

import com.project.my.parser.impl.UserParserImpl;
import com.project.my.service.reader.impl.UserReaderServiceImpl;
import com.project.my.validation.UserValidatorImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserReadServiceImplTest {

    private Path tempFile;
    private UserReaderServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = Files.createTempFile("array", ".txt");

        service = new UserReaderServiceImpl(
                new UserValidatorImpl(),
                new UserFactoryImpl(),
                new UserParserImpl()
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void readLines_validFile_returnsAllLines() throws Exception {
        Files.writeString(tempFile, "1 2 3\n4 5 6");

        List<String> lines = service.readLines(tempFile.toString());

        assertEquals(2, lines.size());
        assertEquals("1 2 3", lines.get(0));
        assertEquals("4 5 6", lines.get(1));
    }

    @Test
    void readUsers_validAndInvalidLines_createsOnlyValidUsers() throws Exception {
        Files.writeString(tempFile,
                "1 2 3\n" +      // valid
                        "x y z\n" +      // invalid
                        "4;5;6\n"        // valid
        );

        List<User> users = service.readUsers(tempFile.toString());

        assertEquals(2, users.size());

        assertArrayEquals(new int[]{1,2,3}, users.get(0).getValues());
        assertArrayEquals(new int[]{4,5,6}, users.get(1).getValues());
    }

    @Test
    void readUsers_emptyFile_returnsEmptyList() throws Exception {
        Files.writeString(tempFile, "");

        List<User> users = service.readUsers(tempFile.toString());

        assertTrue(users.isEmpty());
    }

    @Test
    void readUsers_allInvalidLines_returnsEmptyList() throws Exception {
        Files.writeString(tempFile,
                "bad;data\n" +
                        "1x 22 y\n"
        );

        List<User> users = service.readUsers(tempFile.toString());

        assertTrue(users.isEmpty());
    }
}
