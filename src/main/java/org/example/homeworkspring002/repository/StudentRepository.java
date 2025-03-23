package org.example.homeworkspring002.repository;

import org.apache.ibatis.annotations.*;
import org.example.homeworkspring002.model.entity.Course;
import org.example.homeworkspring002.model.entity.Student;
import org.example.homeworkspring002.model.request.StudentRequest;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "course", column = "student_id", many = @Many(select = "org.example.homeworkspring002.repository.StudentCourseRepository.getAllCourseByStudentId"))
    })

    @Select("""
        SELECT * FROM students
        OFFSET #{offset} LIMIT #{size};
    """)

    List<Student> getAllStudent(int offset, int size);

    @ResultMap("studentMapper")
    @Select("""
        SELECT * FROM students WHERE student_id = #{studentId};
    """)

    Student getStudentById(Long studentId);


    @ResultMap("studentMapper")
    @Select("""
        INSERT INTO students VALUES (default, #{req.studentName}, #{req.email}, #{req.phoneNumber})
        RETURNING *;
    """)

    Student saveStudent(@Param("req") StudentRequest request);

    @ResultMap("studentMapper")
    @Update("""
    UPDATE students
    SET student_name = #{req.studentName},
        email = #{req.email},
        phone_number = #{req.phoneNumber}
    WHERE student_id = #{studentId}
""")
    int updateStudentById(@Param("studentId") Long studentId, @Param("req") StudentRequest request);


    @ResultMap("studentMapper")
    @Delete("DELETE FROM students WHERE student_id = #{studentId}")

    Long deleteStudentById(Long studentId);
}
