package com.ves.platform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import com.ves.platform.auth.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tuition")
public class Tuition {
    @Id
    @Column(name = "tuition_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tuition;

    @Column(name = "date")
    private LocalDate date_tuition;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Tuition(LocalDate date_tuition, User user, Course course) {
        this.date_tuition = date_tuition;
        this.user = user;
        this.course = course;
    }
}
