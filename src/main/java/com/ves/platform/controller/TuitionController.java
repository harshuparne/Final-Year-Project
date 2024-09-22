package com.ves.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ves.platform.auth.User;
import com.ves.platform.auth.UserRepository;
import com.ves.platform.model.Course;
import com.ves.platform.repositories.CourseRepository;
import com.ves.platform.services.core.impl.TuitionService;

@Controller
@RequestMapping("/tuition")
@PreAuthorize("hasRole('ROLE_USER')")
public class TuitionController {

    private TuitionService tuitionService;
    private UserRepository userRepository;
    private CourseRepository courseRepository;

    @Autowired
    public TuitionController(TuitionService tuitionService, UserRepository userRepository, CourseRepository courseRepository) {
        this.tuitionService = tuitionService;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/save/{id_course}")
    public String saveTuition(@PathVariable Long id_course, Authentication authentication, Model model) {
        try {
            String username = authentication.getName();
            tuitionService.createTuition(id_course, username);
            User user = userRepository.findByUsername(username);
            Course course = courseRepository.findById(id_course).get();
            model.addAttribute("course", course);
            model.addAttribute("user", user);
            return "tuition-success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
