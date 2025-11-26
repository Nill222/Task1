package com.project.my.validation;

import com.project.my.entity.User;

public class UserValidatorImpl implements UserValidator{
    @Override
    public boolean isValidLine(User user) {
        if(user == null) {return false;}
        if(!isValidId(user.getId())) {return false;}
        if(!isValidSalary(user.getSalary())) {return false;}
        if(!isValidAge(user.getAge())) {return false;}
        return true;
    }

    @Override
    public boolean isValidId(Integer id) {
        return id != null && id > 0;
    }

    @Override
    public boolean isValidAge(int age) {
        return age > 0;
    }

    @Override
    public boolean isValidSalary(int salary) {
        return salary >= 0;
    }

    @Override
    public boolean isValidLine(String line) {
        if (line == null) {
            return false;
        }
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return false;
        }

        if (!containsOnlyAllowedChars(trimmed)) {
            return false;
        }

        String[] parts = trimmed.split("[;,\\-\\s]+");

        if (!hasThreeNumericParts(parts)) {
            return false;
        }

        try {
            Integer id = Integer.valueOf(parts[0].trim());
            int salary = Integer.parseInt(parts[1].trim());
            int age = Integer.parseInt(parts[2].trim());
            return canCreateUser(id, age, salary);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean isValidArray(User[] users) {
        if (users == null || users.length == 0) {
            return false;
        }
        for (User u : users) {
            if (!isValidLine(u)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canCreateUser(Integer id, int age, int salary) {
        return isValidId(id) && isValidAge(age) && isValidSalary(salary);
    }

    private boolean containsOnlyAllowedChars(String line) {
        return line.matches("^[0-9\\s;,\\-]+$");
    }

    private boolean hasThreeNumericParts(String[] parts) {
        if (parts == null || parts.length != 3) {
            return false;
        }
        for (String p : parts) {
            String t = p.trim();
            if (t.isEmpty()) {
                return false;
            }
            if (!t.matches("^-?\\d+$")) {
                return false;
            }
        }
        return true;
    }
}
