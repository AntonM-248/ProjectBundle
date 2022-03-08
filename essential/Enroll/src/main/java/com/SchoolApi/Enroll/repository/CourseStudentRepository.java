package com.SchoolApi.Enroll.repository;

import com.SchoolApi.Enroll.model.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
}
