package com.springboot.springbootdemo.controllers;

import com.github.javafaker.Faker;
import com.springboot.springbootdemo.models.Student;
import com.springboot.springbootdemo.singletons.FakerSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class StudentController {

    Faker faker = FakerSingleton.getFaker();

    @Autowired
    FakerSingleton wiredFaker;

    //static uris
    @GetMapping("/oneStudent")
    public Student getStudent() {
        return new Student(faker.name().firstName(), faker.name().lastName());
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return IntStream.range(0, 5).mapToObj(x -> new Student(faker.name().firstName(), faker.name().lastName()))
                .collect(Collectors.toList());
    }

    //path variables
    @GetMapping("/{firstName}/{lastName}")
    public Student nameStudent(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return new Student(firstName, lastName);
    }

    @GetMapping("/students/{number}")
    public List<Student> createNStudents(@PathVariable("number") int number) {
        List<Student> students = new ArrayList<>();
        IntStream.range(0, number)
                .forEach(x -> students.add(new Student(faker.name().firstName(), faker.name().lastName())));
        return students;
    }

    @GetMapping("/students/{number}/{index}/{firstName}/{lastName}")
    public List<Student> createNStudentsNameOne(@PathVariable("number") int number, @PathVariable("index") int index,
                                                @PathVariable("firstName") String firstName,
                                                @PathVariable("lastName") String lastName) {
        List<Student> students = new ArrayList<>();
        IntStream.range(0, number)
                .forEach(x -> students.add(new Student(faker.name().firstName(), faker.name().lastName())));
        if (index < number) {
            students.set(index, new Student(firstName, lastName));
        }
        return students;
    }

    @GetMapping("/students/{length}/{stringToRemove}")
    public List<Student> studentsExcludingOneChar(@PathVariable("length") int length,
                                                  @PathVariable("stringToRemove") String stringToRemove) {
        List<Student> students = new ArrayList<>();
        IntStream.range(0, length)
                .forEach(x -> students.add(new Student(faker.name().firstName(), faker.name().lastName())));
        return students.stream().filter(s ->
                        !s.getFirstName().toLowerCase(Locale.ROOT).contains(stringToRemove.toLowerCase(Locale.ROOT)) &&
                                !s.getLastName().toLowerCase(Locale.ROOT).contains(stringToRemove.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    //query parameters
    //ex: localhost:8080/student?firstName=John&lastName=Lohn
    @GetMapping("/student")
    public Student studentQueryParam(@RequestParam(name = "firstName") String firstName,
                                     @RequestParam(name = "lastName") String lastName) {
        return new Student(firstName, lastName);
    }

    @GetMapping("/student/removeQuery")
    public Student studentQueryParam(@RequestParam(name = "firstName") String firstName,
                                     @RequestParam(name = "lastName") String lastName,
                                     @RequestParam(name = "toRemove") String toRemove) {
        return new Student(firstName.replaceAll(toRemove, ""), lastName.replaceAll(toRemove, ""));
    }

    @GetMapping("/customStudents/{amount}")
    public List<Student> getCustomStudents(@PathVariable("amount") int amount,
                                           @RequestParam(name = "stringToRemove") String stringToRemove) {
        return IntStream.range(0, amount)
                .mapToObj(x -> new Student(wiredFaker.getFirstName(), wiredFaker.getLastName())).filter(s ->
                        !s.getFirstName().toLowerCase(Locale.ROOT).contains(stringToRemove.toLowerCase(Locale.ROOT)) &&
                                !s.getLastName().toLowerCase(Locale.ROOT)
                                        .contains(stringToRemove.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

}
