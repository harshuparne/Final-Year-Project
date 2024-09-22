package com.ves.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String imgurl;
}
