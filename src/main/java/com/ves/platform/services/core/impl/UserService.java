package com.ves.platform.services.core.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ves.platform.auth.AuthGroup;
import com.ves.platform.auth.AuthGroupRepository;
import com.ves.platform.auth.User;
import com.ves.platform.auth.UserRepository;
import com.ves.platform.dto.UserDto;

import java.time.LocalDate;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;

    public void createUser(UserDto userDto) throws IllegalStateException {

        if (null != userRepository.findByUsername(userDto.getUsername())) {
            throw new IllegalStateException("There is already a user with the name " + userDto.getUsername());
        } else if (null != userRepository.findByEmail(userDto.getEmail())) {
            throw new IllegalStateException("There is already a user with the email " + userDto.getEmail());
        }
        String username = userDto.getUsername();
        String password = new BCryptPasswordEncoder(11).encode(userDto.getPassword());
        String firstname = userDto.getFirstname();
        String lastname = userDto.getLastname();
        String email = userDto.getEmail();
        log.info("Getting image");
        log.info("About to upload");
        String imgurl = userDto.getImgurl();
        LocalDate date = LocalDate.now();
        User user = new User(username, password, firstname, lastname, email, imgurl, date);
        AuthGroup group = new AuthGroup();

        group.setUsername(userDto.getUsername());
        group.setAuthgroup("USER");

        userRepository.save(user);
        authGroupRepository.save(group);
    }

    public void update(User user) {
        User current = userRepository.findByUsername(user.getUsername());

        current.setFirstname(user.getFirstname());
        current.setLastname(user.getLastname());
        current.setEmail(user.getEmail());
        current.setImgurl(user.getImgurl());

        userRepository.save(current);
    }

    public void patch(User user) {
        User current = userRepository.findByUsername(user.getUsername());

        current.setDetails(user.getDetails());

        userRepository.save(current);
    }
}
