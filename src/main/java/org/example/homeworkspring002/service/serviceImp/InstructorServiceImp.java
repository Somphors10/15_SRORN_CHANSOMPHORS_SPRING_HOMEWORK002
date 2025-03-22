package org.example.homeworkspring002.service.serviceImp;

import org.example.homeworkspring002.model.apiRespone.ApiResponse;
import org.example.homeworkspring002.model.entity.Instructor;
import org.example.homeworkspring002.model.request.InstructorRequest;
import org.example.homeworkspring002.repository.InstructorRepository;
import org.example.homeworkspring002.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImp implements InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImp(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }


    @Override
    public List<Instructor> getAllInstructors(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return instructorRepository.getAllInstructor(offset, size);
    }

    public Instructor saveInstructor(InstructorRequest request) {
        return instructorRepository.saveInstructor(request);
    }

    @Override
    public Instructor getInstructorsBy(Long instructorId) {
        return instructorRepository.getInstructorById(instructorId);
    }

    @Override
    public Instructor updateInstructorById(Long instructorId, InstructorRequest request) {
        return instructorRepository.updateInstructorById(instructorId, request);
    }

    @Override
    public String deleteInstructorById(Long instructorId) {
        Long rowsDeleted = instructorRepository.deleteInstructorById(instructorId);
        if (rowsDeleted > 0) {
            return "Deleted instructor with id " + instructorId;
        } else {
            return "Instructor with id " + instructorId + " not found";
        }
    }


}
