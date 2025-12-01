    package com.project.my.specification;

    import com.project.my.entity.User;

    public interface UserSpecification {
        boolean isSatisfiedBy(User user);
    }
