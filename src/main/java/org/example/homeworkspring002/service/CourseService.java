package org.example.homeworkspring002.service;

import org.example.homeworkspring002.model.entity.Course;
import org.example.homeworkspring002.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses(Integer page, Integer size);

    Course saveBook(CourseRequest request);

    Course getCourseById(Long courseId);

    Course updateCourseById(Long courseId, CourseRequest request);

    String deleteCourseById(Long courseId);
}
