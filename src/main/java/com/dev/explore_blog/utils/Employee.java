package com.dev.explore_blog.utils;

import java.util.Date;

public class Employee {

    private String name;

    private String gender;

    private Double salary;

    private Date joiningDate;

    public Employee(String name, String gender, Double salary, Date joiningDate) {
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", salary=" + salary +
                ", joiningDate=" + joiningDate +
                '}';
    }
}
