package com.ves.platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ves.platform.model.Course;
import com.ves.platform.model.Teacher;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByNameCourse(String name);
    List<Course> findAllByTeacher(Teacher teacher);
}
