package org.example.homeworkspring002.service.serviceImp;

import org.example.homeworkspring002.model.entity.Course;
import org.example.homeworkspring002.model.request.CourseRequest;
import org.example.homeworkspring002.repository.CourseRepository;
import org.example.homeworkspring002.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return courseRepository.getAllCourses(offset, size);
    }

    @Override
    public Course saveBook(CourseRequest request) {
        return courseRepository.saveCourse(request);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public Course updateCourseById(Long courseId, CourseRequest request) {
        return courseRepository.updateCourseById(courseId, request);
    }

    @Override
    public String deleteCourseById(Long courseId) {
        Long rowsDeleted = courseRepository.deleteCourseById(courseId);
        if (rowsDeleted > 0) {
            return "Deleted instructor with id " + courseId;
        } else {
            return "Instructor with id " + courseId + " not found";
        }
    }

}
