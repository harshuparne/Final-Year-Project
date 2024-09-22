package com.ves.platform.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_course;

    @Column(name = "name", nullable = false, unique = true)
    private String nameCourse;

    @Column(name = "description")
    private String descriptionCourse;

    @Column(name = "details")
    private String detailsCourse;

    @Column(name = "difficulty")
    private String difficultyCourse;

    @Column(name = "url")
    private String urlCourse;

    private String imgurl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Teacher teacher;

    public Course(String nameCourse, String descriptionCourse, String detailsCourse, String difficultyCourse, String urlCourse, String imgurl, Teacher teacher) {
        this.nameCourse = nameCourse;
        this.descriptionCourse = descriptionCourse;
        this.detailsCourse = detailsCourse;
        this.difficultyCourse = difficultyCourse;
        this.urlCourse = urlCourse;
        this.imgurl = imgurl;
        this.teacher = teacher;
    }
}
