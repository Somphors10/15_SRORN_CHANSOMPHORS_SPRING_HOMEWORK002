package org.example.homeworkspring002.service;

import org.example.homeworkspring002.model.entity.Instructor;
import org.example.homeworkspring002.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {

    List<Instructor> getAllInstructors(Integer page, Integer size);

    Instructor saveInstructor(InstructorRequest request);

    Instructor getInstructorsBy(Long instructorId);

    Instructor updateInstructorById(Long instructorId, InstructorRequest request);

    String deleteInstructorById(Long instructorId);
}
