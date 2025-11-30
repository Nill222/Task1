    package com.project.my.entity.specification;

    import com.project.my.entity.User;

    public interface UserSpecification {
        boolean isSatisfiedBy(User user);
    }
