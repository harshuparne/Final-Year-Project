package com.ves.platform.services.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ves.platform.dto.TeacherDto;
import com.ves.platform.model.Teacher;
import com.ves.platform.repositories.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void create(TeacherDto teacherDto) {
        String firstname = teacherDto.getFirstname();
        String lastname = teacherDto.getLastname();
        String email = teacherDto.getEmail();
        String description = teacherDto.getDescription();
        String imgurl = teacherDto.getImgurl();
        Teacher teacher = new Teacher(firstname, lastname, email, description, imgurl);

        teacherRepository.save(teacher);
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public void update(Teacher teacher) {
        Teacher currentTeacher = teacherRepository.findById(teacher.getId_teacher()).get();

        currentTeacher.setFirstnameTeacher(teacher.getFirstnameTeacher());
        currentTeacher.setLastnameTeacher(teacher.getLastnameTeacher());
        currentTeacher.setEmailTeacher(teacher.getEmailTeacher());
        currentTeacher.setDescTeacher(teacher.getDescTeacher());
        currentTeacher.setImgurl(teacher.getImgurl());

        teacherRepository.save(currentTeacher);
    }

    public void patch(Teacher teacher) {
        Teacher current = teacherRepository.findById(teacher.getId_teacher()).get();

        current.setDetailsTeacher(teacher.getDetailsTeacher());

        teacherRepository.save(current);
    }

    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

}
