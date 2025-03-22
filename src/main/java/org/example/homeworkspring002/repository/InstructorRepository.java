package org.example.homeworkspring002.repository;

import org.apache.ibatis.annotations.*;
import org.example.homeworkspring002.model.apiRespone.ApiResponse;
import org.example.homeworkspring002.model.entity.Instructor;
import org.example.homeworkspring002.model.request.InstructorRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name")
    })

    @Select("""
        SELECT * FROM instructors
        OFFSET #{offset} LIMIT #{size};
    """)

    List<Instructor> getAllInstructor(int offset, Integer size);

    @ResultMap("instructorMapper")
    @Select("""
    INSERT INTO instructors VALUES (default, #{req.instructorName}, #{req.email})
    RETURNING *;
    """)

    Instructor saveInstructor(@Param("req") InstructorRequest request);

    @Select("""
        SELECT * FROM instructors WHERE instructor_id = #{instructorId};
    """)

    @ResultMap("instructorMapper")
    Instructor getInstructorById(Long instructorId);


    @ResultMap("instructorMapper")
    @Select("""
            UPDATE instructors SET instructor_name = #{req.instructorName}, email = #{req.email}  WHERE instructor_id = #{instructorId}
            RETURNING *;
    """)

    Instructor updateInstructorById(Long instructorId, @Param("req") InstructorRequest request);


    @ResultMap("instructorMapper")
    @Delete("DELETE FROM instructors WHERE instructor_id = #{instructorId}")
    Long deleteInstructorById(Long instructorId);
}
