package com.dev.explore_blog.utils;

import com.dev.explore_blog.entity.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiTut {

    public static void main(String[] args) {

//        //---------------------------------------->
//       List<Integer> list = Arrays.asList(35,50,67,49,24,29,31,28,32);
//
//////even numbers
////        list.stream().filter(value -> value % 2 == 0).forEach(System.out::println);
////
//////multiply by two
////        list.stream().map(value->value*2).forEach(System.out::println);
//
//        //Select only passed students
//        System.out.println("list" + list);
//        List<Integer> passedList = list.stream().filter(i->i>35).toList();
//        System.out.println("passed list" + passedList);
//
//        //Add 5 grace mark to all failed
//        List<Integer> graceMarkAddedList = list.stream().filter(i->i<35).map(j->j+5).toList();
//        System.out.println("grace mark added list" + graceMarkAddedList);
//
//        //Failed marks count
//        long failedCount = list.stream().filter(i->i<35).count();
//        System.out.println("Failed marks count " + failedCount);
//
//        //Passed marks count
//        long passedCount = list.stream().filter(i->i>=35).count();
//        System.out.println("Passed marks count " + passedCount);
//
//        //asc
//        List<Integer> asc = list.stream().sorted().toList();
//        System.out.println("asc " + asc);
//
//        //desc
//        List<Integer> desc = list.stream().sorted(Comparator.reverseOrder()).toList();
//        System.out.println("desc " + desc);
//        //---------------------------------------->


        List<String> stringList = Arrays.asList("A","AAA","B","BB","CC", "CCC");
        Comparator<String> comparator = (a,b)->{
            int leng1 = a.length();
            int leng2 = b.length();
            return Integer.compare(leng1,leng2);
        };

        //sorting natural
        List<String> asc = stringList.stream().sorted().toList();
        System.out.println("asc "+ asc);

        //sorting natural passing comparator
        List<String> asc1 = stringList.stream().sorted(comparator).toList();
        System.out.println("asc "+ asc1);

//sorting natural based on sting length
        List<String> asc2 = stringList.stream().sorted(Comparator.comparingInt(String::length)).toList();
        System.out.println("asc "+ asc2);

        //sorting desc
        List<String> desc = stringList.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println("desc "+ desc);

        //max
        String max = stringList.stream().max(Comparator.reverseOrder()).get();
        System.out.println("max "+ max);

        //min
        String min = stringList.stream().min(Comparator.reverseOrder()).get();
        System.out.println("min "+ min);






        //---------------------------------------------->

//        List<Person> userList = getPeople();
//
//        List<Integer> integerList =new ArrayList<Integer>() {{add(8);add(5);add(1);add(9);add(2);}};
//
//        System.out.println("size of int list " + (long) integerList.size());
//
//        integerList = integerList.stream().sorted().toList();
//        System.out.println("sorted int list " + integerList);
//
//        integerList = integerList.stream().sorted(Comparator.reverseOrder()).toList();
//        System.out.println("sorted int list in desc " + integerList);
//
//        userList = userList.stream().sorted(Comparator.comparingInt(Person::getAge)).toList();
//        System.out.println("sorted user list based on age " + userList);
//
//        userList = userList.stream().sorted(Comparator.comparing(Person::getAge).reversed()).toList();
//        System.out.println("sorted user list based on age desc " + userList);
//
//        userList = userList.stream().sorted(Comparator.comparing(Person::getName)).toList();
//        System.out.println("sorted user list based on name " + userList);
//
//        userList = userList.stream().sorted(Comparator.comparing(Person::getName).reversed()).toList();
//        System.out.println("sorted user list based on age name " + userList);
//
//        userList.stream().map(Person::getDepartment).distinct().forEach(System.out::println);
//    }
//
//    private static List<Person> getPeople() {
//        Person person = new Person();
//        person.setName("Arun");
//        person.setAge(15);
//        person.setGender("male");
//        person.setDepartment("IT");
//        Person person1 = new Person();
//        person1.setName("Sneha");
//        person1.setAge(25);
//        person1.setGender("female");
//        person1.setDepartment("Teaching");
//        Person person2 = new Person();
//        person2.setName("Rakesh");
//        person2.setAge(35);
//        person2.setGender("male");
//        person2.setDepartment("Medical");
//        Person person3 = new Person();
//        person3.setName("Amala");
//        person3.setAge(45);
//        person3.setGender("female");
//        person3.setDepartment("Homemade");
//        Person person4 = new Person();
//        person4.setName("Sachin");
//        person4.setAge(55);
//        person4.setGender("male");
//        person4.setDepartment("Gov");
//        List<Person> userList = new ArrayList<>() {{add(person);add(person1);add(person2);add(person3);add(person4);}};
//        return userList;
    }
}
