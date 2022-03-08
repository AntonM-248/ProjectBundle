package com.SchoolApi.Enroll.controller;

import com.SchoolApi.Enroll.model.Course;
import com.SchoolApi.Enroll.model.CourseStudent;
import com.SchoolApi.Enroll.model.Subject;
import com.SchoolApi.Enroll.repository.CourseRepository;
import com.SchoolApi.Enroll.repository.SubjectRepository;
import com.SchoolApi.Enroll.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/fill")
    public void fill(){
        teacherRepository.findAll().forEach(t -> {
            subjectRepository.findAll().forEach(s -> {
                courseRepository.saveAndFlush(new Course((long)0, s, t, new HashSet<CourseStudent>()));
            });
        });
    }

    @GetMapping()
    public List<Course> getCourseForSubject(@RequestBody Subject subject){
        return courseRepository.findAll().stream()
                .filter(c -> c.getSubject().getSubject().equals(subject.getSubject()))
                .toList();
    }

    @GetMapping("{subject}")
    public List<Course> searchCoursesBySubject(@PathVariable("subject") String subject){
        return courseRepository.findAll().stream()
                .filter(c -> c.getSubject().getSubject().toLowerCase().contains(subject.toLowerCase()))
                .filter(c -> c.getCourseStudents().size() < 30)
                .toList();
    }

}
