package com.ves.platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ves.platform.auth.User;
import com.ves.platform.model.Course;
import com.ves.platform.model.Tuition;

import java.util.List;

public interface TuitionRepository extends JpaRepository<Tuition, Long> {

    List<Tuition> findAllByCourse(Course course);
    List<Tuition> findAllByUser(User user);
    Tuition findByCourseAndUser(Course course, User user);
}
