package com.SchoolApi.Enroll.controller;

import com.SchoolApi.Enroll.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;

    @PostMapping("/fill")
    public void fill(){

    }
}
