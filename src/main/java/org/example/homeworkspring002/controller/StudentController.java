package org.example.homeworkspring002.controller;

import org.example.homeworkspring002.model.apiRespone.ApiResponse;
import org.example.homeworkspring002.model.entity.Student;
import org.example.homeworkspring002.model.request.StudentRequest;
import org.example.homeworkspring002.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    //Get all student
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getStudents(@RequestParam(defaultValue = "1") Integer page,
                                                                  @RequestParam(defaultValue = "10") Integer size) {
        List<Student> students = studentService.getAllStudent(page, size);
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .message("All student have been successfully fetched.")
                .payload(students)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // get student by ID
    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") Long studentId){
        Student student = studentService.getStudentById(studentId);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully founded.")
                .payload(student)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //create student
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> saveStudent(@RequestBody StudentRequest request){
        Student student = studentService.saveStudent(request);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully added.")
                .payload(student)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //update student
    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudentById(@PathVariable("student-id") Long studentId, @RequestBody StudentRequest request){
        Student student = studentService.updateStudentById(studentId, request);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully updated.")
                .payload(student)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // deleted student
    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<String>> deleteStudentById(@PathVariable("student-id") Long studentId){
            String message = studentService.deletedStudentById(studentId);

            ApiResponse<String> response = ApiResponse.<String>builder()
                    .message("The student has been successfully removed.")
                    .status(HttpStatus.OK)
                    .timestamp(LocalDateTime.now())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
