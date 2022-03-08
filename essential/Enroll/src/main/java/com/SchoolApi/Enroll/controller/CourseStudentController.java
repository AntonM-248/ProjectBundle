package com.SchoolApi.Enroll.controller;

import com.SchoolApi.Enroll.exception.ResourceNotFoundException;
import com.SchoolApi.Enroll.model.Course;
import com.SchoolApi.Enroll.model.CourseStudent;
import com.SchoolApi.Enroll.model.Student;
import com.SchoolApi.Enroll.repository.CourseRepository;
import com.SchoolApi.Enroll.repository.CourseStudentRepository;
import com.SchoolApi.Enroll.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// http://localhost:3000/api/v1/courseStudent
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/courseStudent")
public class CourseStudentController {
    @Autowired
    CourseStudentRepository courseStudentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/fill")
    public void fill(){
        studentRepository.findAll().forEach(s -> {
            for(int i = 0; i < 5; i++) {
                courseRepository.findAll().forEach(c ->{
                    if(courseStudentRepository.findAll().stream().filter(cs -> cs.getCourse().getId() == c.getId()).count() < 30
                        && courseStudentRepository.findAll().stream().filter(cs -> cs.getStudent().getId() == s.getId()).count() < 5){
                        courseStudentRepository.saveAndFlush(new CourseStudent((long) 0, s, c));
                    }
                });
            }
        });
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        courseStudentRepository.deleteAll();
    }

    @GetMapping("/all")
    public List<CourseStudent> getAllCourseStudents(){
        return courseStudentRepository.findAll();
    }

    @GetMapping("/{courseId}/{studentId}")
    public Boolean existsCourseStudent(@PathVariable("courseId") long courseId, @PathVariable("studentId") long studentId){
        return courseStudentRepository.findAll().stream()
                .anyMatch(cs -> cs.getCourse().getId() == courseId && cs.getStudent().getId() == studentId);

    }

    @PostMapping("/{courseId}/{studentId}")
    public Boolean enrollStudent(@PathVariable("courseId") long courseId, @PathVariable("studentId") long studentId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found " + courseId));
        if(!courseStudentRepository.findAll().stream()
                .anyMatch(cs -> cs.getCourse().getId() == courseId && cs.getStudent().getId() == studentId)
                && student.getCourseStudents().size() < 5
                && course.getCourseStudents().size() < 30) {
            courseStudentRepository.saveAndFlush(new CourseStudent((long) 0, student, course));
            return true;
        }
        return false;
    }

    @DeleteMapping("/{courseId}/{studentId}")
    public Boolean unenrollStudent(@PathVariable("courseId") long courseId, @PathVariable("studentId") long studentId){
        Optional<CourseStudent> courseStudent = courseStudentRepository.findAll().stream()
                .filter(cs -> cs.getStudent().getId() == studentId && cs.getCourse().getId() == courseId)
                .findAny();
        if(courseStudent.isPresent()){
            courseStudentRepository.deleteById(courseStudent.get().getId());
            return true;
        }
        return false;
    }
}
