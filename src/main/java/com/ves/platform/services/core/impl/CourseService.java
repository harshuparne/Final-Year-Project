package com.ves.platform.services.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ves.platform.dto.CourseDto;
import com.ves.platform.model.Course;
import com.ves.platform.model.Teacher;
import com.ves.platform.repositories.CourseRepository;

import java.util.List;

@Service
public class CourseService{

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void create(CourseDto courseDto) throws Exception{
        if (null != courseRepository.findByNameCourse(courseDto.getNameCourse())) {
            throw new Exception("There is already a course with the name " + courseDto.getNameCourse());
        }
        String nameCourse = courseDto.getNameCourse();
        String descCourse = courseDto.getDescCourse();
        String detailsCourse = courseDto.getDetails();
        String difCourse = courseDto.getDifficulty();
        String urlCourse = courseDto.getUrl();
        String imgurl = courseDto.getImgurl();
        Teacher teacher = courseDto.getTeacher();
        Course course = new Course(nameCourse, descCourse, detailsCourse, difCourse, urlCourse, imgurl, teacher);

        courseRepository.save(course);
    }

    public void update(Course course, Long id_course) {
        Course currentCourse = courseRepository.findById(id_course).get();

            currentCourse.setNameCourse(course.getNameCourse());
            currentCourse.setDescriptionCourse(course.getDescriptionCourse());
            currentCourse.setDetailsCourse(course.getDetailsCourse());
            currentCourse.setDifficultyCourse(course.getDifficultyCourse());
            currentCourse.setUrlCourse(course.getUrlCourse());
            currentCourse.setImgurl(course.getImgurl());
            currentCourse.setTeacher(course.getTeacher());

            courseRepository.save(currentCourse);

    }

    public void delete(Course course) { courseRepository.delete(course); }


    public List<Course> getAll() {
        return courseRepository.findAll();
    }

}
