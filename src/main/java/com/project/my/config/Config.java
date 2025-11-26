package com.project.my.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream input = Config.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("конфиг не найден");
            }

            PROPS.load(input);

        } catch (IOException e) {
            throw new RuntimeException("ошибка загрузки конфига", e);
        }
    }

    public static String get(String key) {
        return PROPS.getProperty(key);
    }
}
