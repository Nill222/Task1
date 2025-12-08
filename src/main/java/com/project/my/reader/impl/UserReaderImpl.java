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
        logger.debug("Чтение файла '{}'.", path);

        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            logger.info("Файл '{}' успешно прочитан. Количество строк: {}", path, lines.size());
            return lines;

        } catch (IOException e) {
            logger.error("Ошибка чтения файла '{}': {}", path, e.getMessage());
            throw new UserException("Ошибка чтения файла: " + path, e);

        }
    }
}
