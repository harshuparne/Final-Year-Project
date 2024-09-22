package com.ves.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {
    private String firstname;
    private String lastname;
    private String email;
    private String description;
    private String imgurl;
}
