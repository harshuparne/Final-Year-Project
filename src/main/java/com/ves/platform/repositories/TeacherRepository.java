package com.ves.platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ves.platform.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
