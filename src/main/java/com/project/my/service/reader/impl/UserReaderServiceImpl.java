package com.project.my.service.reader.impl;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.factory.UserFactory;
import com.project.my.parser.UserParser;
import com.project.my.service.reader.UserReaderService;
import com.project.my.validation.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserReaderServiceImpl implements UserReaderService {

    private static final Logger logger = LogManager.getLogger();

    private final UserValidator validator;
    private final UserFactory factory;
    private final UserParser userParser;

    public UserReaderServiceImpl(UserValidator validator, UserFactory factory, UserParser userParser) {
            this.validator = validator;
            this.factory = factory;
            this.userParser = userParser;
    }

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

    @Override
    public List<User> readUsers(String path) throws UserException {
        logger.debug("Начало обработки пользователей из файла {}", path);

        List<String> lines = readLines(path);
        List<User> users = new ArrayList<>();

        for (String line : lines) {

            processLine(line, users);
        }

        logger.info("Файл '{}' обработан. Успешно создано пользователей: {}", path, users.size());

        return users;
    }

    private void processLine(String line, List<User> users) throws UserException {
        if (line == null || line.isBlank()) {
            logger.debug("Пропущена пустая строка");
            return;
        }

        if (!validator.isValidLine(line)) {
            logger.warn("Некорректная строка пропущена: '{}'", line);
            return;
        }

        int[] values = userParser.parse(line);

        try {
            if (!validator.canCreateUser(values)) {
                logger.warn("Строка прошла базовую проверку, но невалидна по полям: '{}'", line);

                return;
            }

            User user = factory.createUser(values);
            users.add(user);

        } catch (NumberFormatException e) {
            logger.warn("Неверный числовой формат в строке '{}': {}", line, e.getMessage());
        }
    }
}
