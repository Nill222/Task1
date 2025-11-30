package com.project.my.service.sort.impl;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.service.sort.UserSortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class UserSortServiceImpl implements UserSortService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void sortByBubble(int[] values) throws UserException {

        logger.debug("Сортировка пользователей методом пузырьков");

        int n = values.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (values[j] > values[j + 1]) {
                    int temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }

        logger.info("Пузырьковая сортировка выполнена");
        logger.debug("Результат: {}", Arrays.toString(values));
    }

    @Override
    public void sortByQuick(int[] values) throws UserException {

        logger.debug("Сортировка пользователей методом быстрой сортировки");

        quickSort(values, 0, values.length - 1);

        logger.info("Быстрая сортировка завершена");
        logger.debug("Результат быстрой сортировки: {}", Arrays.toString(values));
    }

    private boolean isUserExist(User[] user) {
        return user == null || user.length == 0;
    }

    private void quickSort(int[] values, int low, int high) {
        if (low < high) {
            int pi = partition(values, low, high);
            quickSort(values, low, pi - 1);
            quickSort(values, pi + 1, high);
        }
    }

    private int partition(int[] values, int low, int high) {
        int pivot = values[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (values[j] < pivot) {
                i++;
                int temp = values[i];
                values[i] = values[j];
                values[j] = temp;
            }
        }
        int temp = values[i + 1];
        values[i + 1] = values[high];
        values[high] = temp;
        return i + 1;
    }
}
