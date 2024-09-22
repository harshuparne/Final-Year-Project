package com.ves.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import com.ves.platform.auth.User;
import com.ves.platform.model.Course;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TuitionDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private User user;
    private Course course;
}
