package com.ves.platform.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    private String details;
    private String imgurl;
    @Column(name = "date_registration")
    private LocalDate date;

    public User(String username, String password, String firstname, String lastname, String email, String imgurl, LocalDate date) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.imgurl = imgurl;
        this.date = date;
    }

    public User(String details) {
        this.details = details;
    }
}
