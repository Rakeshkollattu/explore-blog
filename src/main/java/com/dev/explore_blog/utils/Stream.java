package com.dev.explore_blog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = simpleDateFormat.parse("01-05-2025");
        Date date2 = simpleDateFormat.parse("05-09-2024");
        Date date3 = simpleDateFormat.parse("04-06-2023");
        Date date4 = simpleDateFormat.parse("09-02-2022");


        List<Employee> empList = List.of(
                new Employee("Pranav", "Male", 25000.00, date1),
                new Employee("Arya", "Female", 28000.00, date2),
                new Employee("Arjun", "Male", 50000.00, date3),
                new Employee("Usha", "Female", 35000.00, date4)
        );


        //Find the employee who has maximum salary
        Employee employee = empList.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(" Employee with maximum salary : " + employee);

        //Find the employee who has minimum salary
        Employee employee1 = empList.stream().min(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(" Employee with minimum salary : " + employee1);

        //Find the employee who has second-highest salary
        Optional<Employee> employee3 = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst();
        System.out.println(" Employee with second-highest salary ");
        employee3.ifPresent(System.out::println);

        //Find the employee who is most senior based on joining date
        Optional<Employee> employee4 = empList.stream().min(Comparator.comparing(Employee::getJoiningDate));
        System.out.println(" Most senior Employee based on joining date");
        employee4.ifPresent(System.out::println);

        //Find new employee based on joining date
        Optional<Employee> employee5 = empList.stream().max(Comparator.comparing(Employee::getJoiningDate));
        System.out.println(" New Employee based on joining date");
        employee5.ifPresent(System.out::println);

        //Count the employee based on gender
        Map<String, Long> count = empList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println("Employee count based on gender :" + count);


    }
}
