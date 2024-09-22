package com.ves.platform.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ves.platform.dto.UserDto;
import com.ves.platform.services.core.impl.UserService;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/register")
    public String newUser(Model model) {
        model.addAttribute("userdto", new UserDto());
        return "register";
    }

    @PostMapping(value = "/register")
    public String saveUser(UserDto userDto, Model model) {
        try {
            userService.createUser(userDto);
            return "registered";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

}
