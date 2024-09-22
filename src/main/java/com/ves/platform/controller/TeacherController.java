package com.ves.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ves.platform.dto.TeacherDto;
import com.ves.platform.model.Course;
import com.ves.platform.model.Teacher;
import com.ves.platform.repositories.CourseRepository;
import com.ves.platform.repositories.TeacherRepository;
import com.ves.platform.services.core.impl.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;

    @Autowired
    public TeacherController(TeacherService teacherService, TeacherRepository teacherRepository,
                              CourseRepository courseRepository) {
        this.teacherService = teacherService;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }
    
    @GetMapping
    public String getTeacheresList(Model model) {
        List<Teacher> teachers = teacherService.getAll();
        model.addAttribute("teachers", teachers);
        return "teachers/teachers";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new TeacherDto());
        return "teachers/teacher-add";
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveTeacher(TeacherDto teacher) {
        teacherService.create(teacher);

        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id_teacher}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getTeacherForUpdate(@PathVariable Long id_teacher,
                                       Model model) {
        try {
            Teacher teacherActual = teacherRepository.findById(id_teacher).get();
            model.addAttribute("teacher", teacherActual);
            return "teachers/teacher-edit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/update/{id_teacher}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateTeacher(@PathVariable Long id_teacher,
                                 Teacher teacher, RedirectAttributes attributes, Model model){

        try {
            Teacher currentTeacher = teacherRepository.findById(id_teacher).get();
            currentTeacher.setFirstnameTeacher(teacher.getFirstnameTeacher());
            currentTeacher.setLastnameTeacher(teacher.getLastnameTeacher());
            currentTeacher.setEmailTeacher(teacher.getEmailTeacher());
            currentTeacher.setDescTeacher(teacher.getDescTeacher());
            currentTeacher.setImgurl(teacher.getImgurl());

            teacherService.update(teacher);
            attributes.addAttribute("id_teacher", id_teacher);

            return "redirect:/teachers/{id_teacher}";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/patch/{id_teacher}")
    //unknown
    public String patchTeacher(@PathVariable Long id_teacher, Teacher teacher, RedirectAttributes attributes, Model model) {

        try {
            Teacher current = teacherRepository.findById(id_teacher).get();
            current.setDetailsTeacher(teacher.getDetailsTeacher());
            teacherService.patch(current);

            attributes.addAttribute("id_teacher", id_teacher);
            return "redirect:/teachers/{id_teacher}";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/delete/{id_teacher}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteTeacher(@PathVariable Long id_teacher, Model model) {
        try {
            Teacher teacherActual = teacherRepository.findById(id_teacher).get();
            teacherService.delete(teacherActual);

            return "redirect:/teachers";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/{id_teacher}")
    public String getTeacherDetail(@PathVariable Long id_teacher, Model model) {
        try {
            Teacher teacher = teacherRepository.findById(id_teacher).get();
            model.addAttribute("teacher", teacher);
            List<Course> courses = courseRepository.findAllByTeacher(teacher);
            model.addAttribute("courses", courses);
            return "teachers/teacher-detail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
