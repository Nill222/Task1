package com.project.my.factory.impl;

import com.project.my.factory.UserIdGenerator;

import java.util.UUID;

public class UserIdGeneratorImpl implements UserIdGenerator {

    @Override
    public Integer generateUserId() {
        return Math.abs(UUID.randomUUID().hashCode());
    }
}
