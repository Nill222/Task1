package com.project.my;

import com.project.my.entity.User;
import com.project.my.exception.UserException;
import com.project.my.factory.UserFactory;
import com.project.my.factory.impl.UserFactoryImpl;
import com.project.my.factory.impl.UserIdGeneratorImpl;
import com.project.my.observer.impl.UserObserverImpl;
import com.project.my.parser.UserParser;
import com.project.my.parser.impl.UserParserImpl;
import com.project.my.reader.UserReader;
import com.project.my.reader.impl.UserReaderImpl;
import com.project.my.repository.impl.UserRepositoryImpl;
import com.project.my.service.UserService;
import com.project.my.service.impl.UserServiceImpl;
import com.project.my.specification.UserSpecification;
import com.project.my.specification.UserSumSpecification;
import com.project.my.validation.UserValidator;
import com.project.my.validation.impl.UserValidatorImpl;
import com.project.my.comparator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws UserException {

        //singleton
        UserRepositoryImpl repository = UserRepositoryImpl.getInstance();

        //observer
        repository.addObserver(new UserObserverImpl());

        //service
        UserReader reader = new UserReaderImpl();
        UserValidator validator = new UserValidatorImpl();
        UserParser parser = new UserParserImpl();
        UserFactory factory = new UserFactoryImpl(new UserIdGeneratorImpl());
        UserService service = new UserServiceImpl();

        //reader
        String path = Paths.get("data", "arrays.txt").toString();
        List<String> lines = reader.readLines(path);

        logger.info("File read complete. Total lines={}", lines.size());

        for (String line : lines) {

            if (!validator.isValidLine(line)) {
                logger.warn("Invalid input skipped: {}", line);
                continue;
            }

            int[] values = parser.parse(line);
            User user = factory.createUser(values);

            repository.add(user);
            logger.info("Added User id={} to repository", user.getId());
        }

        //specification
        logger.info("SEARCH sum > 10");
        UserSpecification spec = new UserSumSpecification(10, service);
        List<User> resultSearch = repository.find(spec);
        resultSearch.forEach(u -> logger.info("Found user id={} with sum > 10", u.getId()));


        //comparators
        logger.info("SORT by ID");
        repository.sorted(new UserCompareById())
                .forEach(u -> logger.info("Sorted by id:  {}", u));

        logger.info("SORT by Length");
        repository.sorted(new UserCompareByLength())
                .forEach(u -> logger.info("Sorted by length: {}", u));

        logger.info("SORT by First Element");
        repository.sorted(new UserCompareByFirstElement())
                .forEach(u -> logger.info("Sorted by first element: {}", u));

        logger.info("SORT by Sum");
        repository.sorted(new UserCompareBySum(service))
                .forEach(u -> logger.info("Sorted by sum: {}", u));

        logger.info("Program Finished");
    }
}
