package com.ves.platform.services.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ves.platform.auth.User;
import com.ves.platform.auth.UserRepository;
import com.ves.platform.model.Course;
import com.ves.platform.model.Tuition;
import com.ves.platform.repositories.CourseRepository;
import com.ves.platform.repositories.TuitionRepository;

import java.time.LocalDate;

@Service
public class TuitionService {

    private TuitionRepository tuitionRepository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;

    @Autowired
    public TuitionService(TuitionRepository tuitionRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.tuitionRepository = tuitionRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public void createTuition(Long id_course, String username) throws Exception {
        Course course = courseRepository.findById(id_course).get();
        User user = userRepository.findByUsername(username);

        if (null != tuitionRepository.findByCourseAndUser(course, user)) {
            throw new Exception("You are already enrolled in this course");
        }
        LocalDate date = LocalDate.now();
        Tuition tuition = new Tuition(date, user, course);
        tuitionRepository.save(tuition);
    }
}
