package org.example.homeworkspring002.controller;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.example.homeworkspring002.model.apiRespone.ApiResponse;
import org.example.homeworkspring002.model.entity.Instructor;
import org.example.homeworkspring002.model.request.InstructorRequest;
import org.example.homeworkspring002.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    // Constructor injection for InstructorService
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    //get instructor
    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(@RequestParam(defaultValue = "1") Integer page,
                                                                           @RequestParam(defaultValue = "10") Integer size) {
        List<Instructor> instructors = instructorService.getAllInstructors(page, size);

        ApiResponse<List<Instructor>> response = ApiResponse.<List<Instructor>>builder()
                .message("All instructors have been successfully fetched.")
                .payload(instructors)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //post instructor
    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> saveInstructor(@RequestBody InstructorRequest request) {
        Instructor savedInstructor = instructorService.saveInstructor(request);

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully add.")
                .payload(savedInstructor)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // get instructor by ID
    @GetMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable("instructor-id") Long instructorId) {
        Instructor getInstructorById = instructorService.getInstructorsBy(instructorId);

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully founded.")
                .payload(getInstructorById)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //update instructor by ID
    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable("instructor-id") Long instructorId, @RequestBody InstructorRequest request) {
        Instructor updatedInstructorById = instructorService.updateInstructorById(instructorId, request);
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully updated.")
                .payload(updatedInstructorById)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

       // Deleted instructor by ID
        @DeleteMapping("/{instructor-id}")
        public ResponseEntity<ApiResponse<String>> deleteInstructorById(@PathVariable("instructor-id") Long instructorId) {
            String message = instructorService.deleteInstructorById(instructorId);

            ApiResponse<String> response = ApiResponse.<String>builder()
                    .message("The instructor has been successfully removed.")
                    .status(HttpStatus.OK)
                    .timestamp(LocalDateTime.now())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
}