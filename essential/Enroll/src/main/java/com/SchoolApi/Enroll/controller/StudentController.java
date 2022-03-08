package com.SchoolApi.Enroll.controller;

import com.SchoolApi.Enroll.exception.ResourceNotFoundException;
import com.SchoolApi.Enroll.model.CourseStudent;
import com.SchoolApi.Enroll.model.Student;
import com.SchoolApi.Enroll.repository.StudentRepository;
import com.SchoolApi.Enroll.service.FakerService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FakerService faker;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @PostMapping("/fill/{num}")
    public void addStudents(@PathVariable("num") int num, @RequestParam(name = "string") String string){
        List<Student> students = IntStream.range(0, num)
                .mapToObj(i -> new Student(0, faker.name().firstName(), "pass",
                        new HashSet<CourseStudent>()))
                .filter(s -> !s.getName().contains(string)).distinct().toList();
        studentRepository.saveAllAndFlush(students);
    }

    @GetMapping("{name}/{pass}")
    public Student getByNameAndPass(@PathVariable("name") String name, @PathVariable("pass") String pass){
        List<Student> students = studentRepository.findAll().stream()
                .filter(s -> s.getName().equals(name) && s.getPassword().equals(pass))
                .toList();
        if(students.size() > 0) return students.get(0);
        throw new ResourceNotFoundException("Student of " + name + "/" + pass + "not found");
    }

    @DeleteMapping("/deleteDupes")
    public void deleteDupes(){
        List<Student> toEliminate = studentRepository.findAll().stream()
                .filter(s -> studentRepository.findAll().stream().
                        filter(stud -> stud.getName().equals(s.getName())).count() != 1)
                .toList();
        studentRepository.deleteAll(toEliminate);
    }

    @GetMapping("/{id}")
    public Set<CourseStudent> getStudentsCourses(@PathVariable("id") long id){
        Student student = studentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Id:" + id + " not found"));
        return student.getCourseStudents();
    }
}

// faker.name().firstName() + " " + faker.name().lastName(), faker.random().toString())