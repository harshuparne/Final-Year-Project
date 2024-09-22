package com.ves.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ves.platform.model.Course;
import com.ves.platform.model.Teacher;
import com.ves.platform.services.core.impl.CourseService;
import com.ves.platform.services.core.impl.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    private TeacherService teacherService;
    private CourseService courseService;

    @Autowired
    public APIController(TeacherService teacherService, CourseService courseService) {
        super();
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeacher() {
        return this.teacherService.getAll();
    }

    @GetMapping("/courses")
    public List<Course> getAllCourse() {
        return this.courseService.getAll();
    }
}
