package com.ves.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ves.platform.auth.User;
import com.ves.platform.auth.UserRepository;
import com.ves.platform.dto.CourseDto;
import com.ves.platform.model.Course;
import com.ves.platform.model.Teacher;
import com.ves.platform.repositories.CourseRepository;
import com.ves.platform.repositories.TuitionRepository;
import com.ves.platform.repositories.TeacherRepository;
import com.ves.platform.services.core.impl.CourseService;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController{

    private CourseService courseService;
    private CourseRepository courseRepository;
    private TuitionRepository tuitionRepository;
    private UserRepository userRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public CourseController(CourseService courseService, CourseRepository courseRepository,
                           TuitionRepository tuitionRepository, UserRepository userRepository, TeacherRepository teacherRepository) {
        super();
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        this.tuitionRepository = tuitionRepository;
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/add/{id_teacher}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCourse(@PathVariable Long id_teacher, Model model) {
        try {
            Teacher current = teacherRepository.findById(id_teacher).get();
            model.addAttribute("course", new CourseDto());
            model.addAttribute("teacher", current);
            return "courses/course-add";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/add/{id_teacher}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCourse(@PathVariable Long id_teacher, CourseDto course, Model model) {
        try {
            Teacher current = teacherRepository.findById(id_teacher).get();
            course.setTeacher(current);
            courseService.create(course);
            return "redirect:/courses";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }

    }

    @GetMapping("/edit/{id_course}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCourseForUpdate(@PathVariable Long id_course, Model model) {
        try {
            Course courseActual = courseRepository.findById(id_course).get();
            model.addAttribute("course", courseActual);
            return "courses/course-edit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/edit/{id_teacher}/{id_course}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateCourse(@PathVariable Long id_teacher, @PathVariable Long id_course, Course course, Model model, RedirectAttributes attributes) {

        try {
            Teacher currentTeacher = teacherRepository.findById(id_teacher).get();
            course.setTeacher(currentTeacher);

            courseService.update(course, id_course);
            attributes.addAttribute("id_course", id_course);

            return "redirect:/courses/{id_course}";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping
    public String getCoursesList(Model model) {
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        return "courses/courses";
    }

    @GetMapping("/delete/{id_course}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCourse(@PathVariable Long id_course, Model model) {
        try {
            Course courseActual = courseRepository.findById(id_course).get();
            courseService.delete(courseActual);

            return "redirect:/courses";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/{id_course}")
    public String getCourseDetail(@PathVariable Long id_course, Authentication authentication, Model model) {
        String username = authentication.getName();
        Boolean registered = false;
        try {
            Course course = courseRepository.findById(id_course).get();
            User user = userRepository.findByUsername(username);
            if (null != tuitionRepository.findByCourseAndUser(course, user)) {
                registered = true;
            }
            model.addAttribute("course", course);
            model.addAttribute("registered", registered);
            return "courses/course-detail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
