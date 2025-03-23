package org.example.homeworkspring002.service.serviceImp;

import org.example.homeworkspring002.model.entity.Student;
import org.example.homeworkspring002.model.request.StudentRequest;
import org.example.homeworkspring002.repository.StudentCourseRepository;
import org.example.homeworkspring002.repository.StudentRepository;
import org.example.homeworkspring002.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    public StudentServiceImp(StudentRepository studentRepository, StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public List<Student> getAllStudent(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return studentRepository.getAllStudent(offset, size);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public Student saveStudent(StudentRequest request) {
        Student student = studentRepository.saveStudent(request) ;
        for (Long courseId : request.getCourseId()) {
            studentCourseRepository.insertStudentIdAndCourseId(student.getStudentId(), courseId);
        }
        return studentRepository.getStudentById(student.getStudentId());
    }

    @Override
    public Student updateStudentById(Long studentId, StudentRequest request) {
        int rowsUpdated = studentRepository.updateStudentById(studentId, request);
        if (rowsUpdated > 0) {
            studentCourseRepository.deleteByStudentId(studentId);
            for (Long courseId : request.getCourseId()) {
                studentCourseRepository.insertStudentIdAndCourseId(studentId, courseId);
            }
            return studentRepository.getStudentById(studentId);
        } else {
            throw new RuntimeException("Student with id " + studentId + " not found or could not be updated.");
        }
    }

    @Override
    public String deletedStudentById(Long studentId) {
        Long rowsDeleted = studentRepository.deleteStudentById(studentId);
        if (rowsDeleted > 0) {
            return "Deleted instructor with id " + studentId;
        } else {
            return "Instructor with id " + studentId + " not found";
        }
    }
}
