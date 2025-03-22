package org.example.homeworkspring002.repository;

import org.apache.ibatis.annotations.*;
import org.example.homeworkspring002.model.entity.Course;
import org.example.homeworkspring002.model.request.CourseRequest;

import java.util.List;

@Mapper
public interface CourseRepository {

    // Map filed and column table
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id", one = @One(select = "org.example.homeworkspring002.repository.InstructorRepository.getInstructorById"))
    })

    // Get data from table courses
    @Select("""
        SELECT * FROM courses
        OFFSET #{offset} LIMIT #{size};
    """)
    List<Course> getAllCourses(int offset, int size);

    // Insert data to courses
    @ResultMap("courseMapper")
    @Select("""
           INSERT INTO courses VALUES (default, #{req.courseName}, #{req.description} ,#{req.instructorId})
           RETURNING *;
    """)

    Course saveCourse(@Param("req") CourseRequest request);

    // Get data from table course by ID
    @ResultMap(("courseMapper"))
    @Select("""
        SELECT * FROM courses WHERE course_id = #{courseId};
    """)

    Course getCourseById(Long courseId);


    // Update data from table course by ID
    @ResultMap("courseMapper")
    @Select("""
            UPDATE courses SET course_name = #{req.courseName}, description = #{req.description}, instructor_id = #{req.instructorId}  WHERE course_id = #{courseId}
            RETURNING *;
    """)
    Course updateCourseById( Long courseId,@Param("req") CourseRequest request);


    // Delete data from table course by ID
    @ResultMap("courseMapper")
    @Delete("DELETE FROM courses WHERE course_id = #{courseId}")
    Long deleteCourseById(Long courseId);

}
