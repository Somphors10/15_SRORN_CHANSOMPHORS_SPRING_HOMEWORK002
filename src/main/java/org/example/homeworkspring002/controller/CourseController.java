package org.example.homeworkspring002.controller;

import org.example.homeworkspring002.model.apiRespone.ApiResponse;
import org.example.homeworkspring002.model.entity.Course;
import org.example.homeworkspring002.model.entity.Instructor;
import org.example.homeworkspring002.model.request.CourseRequest;
import org.example.homeworkspring002.model.request.InstructorRequest;
import org.example.homeworkspring002.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //get all course
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam(defaultValue = "1") Integer page,
                                                                   @RequestParam(defaultValue = "10") Integer size){
        List<Course> courses = courseService.getAllCourses(page, size);
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .message("All courses have been successfully fetched.")
                .payload(courses)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //post course
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> saveCourse(@RequestBody CourseRequest request){
        Course saveCourse = courseService.saveBook(request);

        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully added.")
                .payload(saveCourse)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // get course by ID
    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("course-id") Long courseId) {
        Course getCourseId = courseService.getCourseById(courseId);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully founded.")
                .payload(getCourseId)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //update course by id
    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable("course-id") Long courseId, @RequestBody CourseRequest request) {
        Course updateCourseById = courseService.updateCourseById(courseId, request);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully updated.")
                .payload(updateCourseById)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    // Deleted cousrse by ID

    @DeleteMapping("/{course-id}")
    public ResponseEntity<ApiResponse<String>> deleteCourseById(@PathVariable("course-id") Long courseId) {
        String message = courseService.deleteCourseById(courseId);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .message("The course has been successfully removed.")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
