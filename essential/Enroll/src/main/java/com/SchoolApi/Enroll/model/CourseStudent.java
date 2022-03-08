package com.SchoolApi.Enroll.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "course_students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("courseStudents")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("courseStudents")
    private Course course;
}
