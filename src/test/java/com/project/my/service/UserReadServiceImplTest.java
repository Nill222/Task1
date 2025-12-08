package com.project.my.service;

import com.project.my.reader.impl.UserReaderImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserReadServiceImplTest {

    private Path tempFile;
    private UserReaderImpl service;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = Files.createTempFile("array", ".txt");
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
}
