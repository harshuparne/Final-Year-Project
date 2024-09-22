package com.ves.platform.dto;

import com.ves.platform.model.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDto {
    private String nameCourse;
    private String descCourse;
    private String difficulty;
    private String details;
    private String url;
    private String imgurl;
    private Teacher teacher;
}
