package com.ves.platform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_teacher;
    @Column(name = "firstname")
    private String firstnameTeacher;
    @Column(name = "lastname")
    private String lastnameTeacher;
    @Column(name = "email")
    private String emailTeacher;
    @Column(name = "description")
    private String descTeacher;
    @Column(name = "details")
    private String detailsTeacher;
    private String imgurl;


    public Teacher(String firstnameTeacher, String lastnameTeacher, String emailTeacher, String descTeacher, String imgurl) {
        this.firstnameTeacher = firstnameTeacher;
        this.lastnameTeacher = lastnameTeacher;
        this.emailTeacher = emailTeacher;
        this.descTeacher = descTeacher;
        this.imgurl = imgurl;
    }

    public Teacher(String detailsTeacher) {
        this.detailsTeacher = detailsTeacher;
    }
}
