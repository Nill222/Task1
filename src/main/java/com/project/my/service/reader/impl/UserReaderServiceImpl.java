package com.project.my.service.reader.impl;

import com.project.my.entity.User;
import com.project.my.factory.UserFactory;
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

    private static final Logger logger = LogManager.getLogger(UserReaderServiceImpl.class);

    private final UserValidator validator;
    private final UserFactory factory;

    public UserReaderServiceImpl(UserValidator validator, UserFactory factory) {
            this.validator = validator;
            this.factory = factory;
    }

    @Override
    public List<String> readLines(String path) {
        logger.debug("Чтение файла '{}'.", path);

        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            logger.info("Файл '{}' успешно прочитан. Количество строк: {}", path, lines.size());
            return lines;

        } catch (IOException e) {
            logger.error("Ошибка чтения файла '{}': {}", path, e.getMessage());
            throw new RuntimeException("Ошибка чтения файла: " + path, e);

        }
    }

    @Override
    public List<User> readUsers(String path) {
        logger.debug("Начало обработки пользователей из файла {}", path);

        List<String> lines = readLines(path);
        List<User> users = new ArrayList<>();

        for (String line : lines) {
            processLine(line, users);
        }

        logger.info("Файл '{}' обработан. Успешно создано пользователей: {}", path, users.size());

        return users;
    }

    private void processLine(String line, List<User> users) {
        if (line == null || line.isBlank()) {
            logger.debug("Пропущена пустая строка");

            return;
        }

        if (!validator.isValidLine(line)) {
            logger.warn("Некорректная строка пропущена: '{}'", line);

            return;
        }

        String[] parts = line.trim().split("[;,\\-\\s]+");
        if (parts.length < 3) {
            logger.warn("Недостаточно данных в строке: '{}'", line);

            return;
        }

        try {
            Integer id = Integer.parseInt(parts[0]);
            int salary = Integer.parseInt(parts[1]);
            int age = Integer.parseInt(parts[2]);

            if (!validator.canCreateUser(id, age, salary)) {
                logger.warn("Строка прошла базовую проверку, но невалидна по полям: '{}'", line);

                return;
            }

            User user = factory.createUser(id, salary, age);
            users.add(user);

            logger.warn("Строка прошла базовую проверку, но невалидна по полям: '{}'", line);


        } catch (NumberFormatException e) {
            logger.warn("Неверный числовой формат в строке '{}': {}", line, e.getMessage());

        }
    }
}
