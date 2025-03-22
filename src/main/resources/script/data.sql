INSERT INTO instructors( instructor_name, email) VALUES
                                                    ('Chea mengLim', 'meanhlim@gmail.com'),
                                                     ('Vong yuoyi', 'yuoyi@gmail.com');


INSERT INTO courses(course_name, description, instructor_id) VALUES
                                                     ('java', 'so hard', 1),
                                                     ('web', 'very hard',1);


INSERT INTO students(student_name, email, phone_number) VALUES
                                                            ('jing', 'jing@gmail.com', '012345567'),
                                                            ('tev', 'tev@gmail.com','045456753');

INSERT INTO student_course(student_id, course_id) VALUES
                                                      (1,10),
                                                      (2,12);
