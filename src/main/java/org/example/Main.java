package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Задача 1: Работа с числами
        System.out.println("---------------Задание №1---------------");
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> newList = new ArrayList<>();
        for (int element : intList) {
            if (element > 0 && element % 2 == 0) {
                newList.add(element);
            }
        }
        newList.sort(Integer::compareTo);
        for (Integer integer : newList) {
            System.out.println(integer);
        }
        //Задача 2: Перепись населения
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println("---------------Задание №2.1---------------");
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("количество несовершеннолетних: " + count);
        System.out.println("---------------Задание №2.2---------------");
        List<String> conscripts = persons.stream()
                .filter(x -> x.getAge() < 27 && x.getAge() >= 18)
                .filter(x -> x.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("список фамилий: ");
        for (String family : conscripts) {
            System.out.println(family);
        }
        System.out.println("---------------Задание №2.3---------------");
        List<String> people = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> (x.getSex() == Sex.MAN ? x.getAge() < 65 : x.getAge() < 60)
                        && x.getAge() >= 18)
                .map(Person::getFamily)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("список фамилий: ");
        for (String family : people) {
            System.out.println(family);
        }
    }
}