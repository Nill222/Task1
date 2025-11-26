package com.project.my.entity;

public class User {

    private Integer id;
    private int salary;
    private int age;

    public User(Integer id, int salary, int age) {
        this.id = id;
        this.salary = salary;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User other = (User) o;

        if (age != other.age) return false;
        if (salary != other.salary) return false;

        if (id != null ? !id.equals(other.id) : other.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (id != null ? id.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", salary='").append(salary);
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
