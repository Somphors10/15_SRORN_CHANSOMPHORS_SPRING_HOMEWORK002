package org.example.homeworkspring002.repository;

import org.apache.ibatis.annotations.*;
import org.example.homeworkspring002.model.entity.Course;
import org.example.homeworkspring002.model.entity.Student;

import java.util.List;

@Mapper
public interface StudentCourseRepository {

    @Results(id = "studentCourseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id", one = @One(select = "org.example.homeworkspring002.repository.InstructorRepository.getInstructorById"))
    })

    // Get student_course by using Inner join
    @Select("""
        SELECT * FROM student_course bc
        INNER JOIN courses c
        ON bc.course_id = c.course_id
        WHERE student_id = #{studentId}
    """)
    List<Course> getAllCourseByStudentId(Long studentId);

    @Insert("""
        INSERT INTO student_course (student_id, course_id) VALUES (#{studentId}, #{courseId})
    """)
    void insertStudentIdAndCourseId(Long studentId, Long courseId);


    @Delete("""
        DELETE FROM student_course
        WHERE student_id = #{studentId}
    """)
    void deleteByStudentId(Long studentId);

}