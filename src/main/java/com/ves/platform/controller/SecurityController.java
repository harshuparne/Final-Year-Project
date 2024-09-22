package com.ves.platform.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ves.platform.auth.User;
import com.ves.platform.auth.UserRepository;
import com.ves.platform.model.Tuition;
import com.ves.platform.repositories.TuitionRepository;
import com.ves.platform.services.core.impl.UserService;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@AllArgsConstructor
public class SecurityController {

    private final UserRepository userRepository;
    private final TuitionRepository tuitionRepository;
    private final UserService userService;

    @GetMapping("/profile")
    public String getUserProfile(Authentication authentication, Model model) {
        try {
            String currentUsername = authentication.getName();
            User user = userRepository.findByUsername(currentUsername);
            List<Tuition> tuitions = tuitionRepository.findAllByUser(user);
            int numCourses = tuitions.size();
            model.addAttribute("user", user);
            model.addAttribute("tuitions", tuitions);
            model.addAttribute("numCourses", numCourses);
            return "user/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/user/edit/{userID}")
    public String getForEdit(@PathVariable Long userID, Authentication authentication, Model model) {

        try {
            String currentusername = authentication.getName();
            User current = userRepository.findById(userID).get();
            if (currentusername.equals(current.getUsername())) {
            model.addAttribute(current);
            return "user/user-edit";
            } else {
                throw new Exception("Authentication Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/user/edit/{userID}")
    public String updateUser(@PathVariable Long userID, Authentication authentication, User user, Model model) {

        try {
            User current = userRepository.findById(userID).get();
            String currentusername = authentication.getName();
            if (currentusername.equals(current.getUsername())) {
                current.setFirstname(user.getFirstname());
                current.setLastname(user.getLastname());
                current.setEmail(user.getEmail());
                current.setImgurl(user.getImgurl());
                userService.update(current);

                return "redirect:/profile";
            } else {
                throw new Exception("Authentication Error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/user/patch/{id_user}")
    public String patchUser(@PathVariable Long id_user, Authentication authentication, User user, Model model) {

        try {
            User current = userRepository.findById(id_user).get();
            String currentusername = authentication.getName();
            if (currentusername.equals(current.getUsername())) {
                current.setDetails(user.getDetails());
                userService.patch(current);

                return "redirect:/profile";
            } else {
                throw new Exception("Authentication Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
