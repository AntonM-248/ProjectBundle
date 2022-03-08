package com.SchoolApi.Enroll.controller;

import com.SchoolApi.Enroll.model.Course;
import com.SchoolApi.Enroll.model.Subject;
import com.SchoolApi.Enroll.repository.SubjectRepository;
import com.SchoolApi.Enroll.service.FakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
    @Autowired
    FakerService fakerService;

    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping("/fill")
    public void fillSubjects(){
        subjectRepository.saveAllAndFlush(IntStream.range(0, 20).mapToObj(i -> new Subject(0, fakerService.educator().course().toString(), new HashSet<Course>())).distinct().limit(4).collect(
                Collectors.toList()));
    }

    @GetMapping("/{string}")
    public List<Subject> searchSubjects(@PathVariable("string") String string){
        return subjectRepository.findAll().stream().filter(s -> s.getSubject().toLowerCase().contains(string.toLowerCase())).toList();
    }
}
