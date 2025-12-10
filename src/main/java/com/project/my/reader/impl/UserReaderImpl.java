package com.project.my.reader.impl;

import com.project.my.exception.UserException;
import com.project.my.reader.UserReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class UserReaderImpl implements UserReader {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<String> readLines(String path) throws UserException {
        logger.debug("Attempting to read file: '{}'", path);

        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            logger.info("File '{}' successfully read. Total lines: {}", path, lines.size());
            return lines;

        } catch (IOException e) {
            logger.error("Failed to read file '{}'. Error: {}", path, e.getMessage());
            throw new UserException("Failed to read file: " + path, e);
        }
    }
}
