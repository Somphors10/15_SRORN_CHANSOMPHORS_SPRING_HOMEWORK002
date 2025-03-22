CREATE TABLE IF NOT EXISTS instructors(
    instructor_id SERIAL PRIMARY KEY ,
    instructor_name VARCHAR(200) NOT NULL ,
    email VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS courses(
    course_id SERIAL PRIMARY KEY ,
    course_name VARCHAR(100) NOT NULL ,
    description VARCHAR(255),
    instructor_id INT NOT NULL ,
    CONSTRAINT fk_instructor FOREIGN KEY (instructor_id)
        REFERENCES instructors (instructor_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS students(
    student_id SERIAL PRIMARY KEY ,
    student_name VARCHAR(100) NOT NULL ,
    email VARCHAR(255) NOT NULL ,
    phone_number VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS student_course(
    student_id INT NOT NULL ,
    course_id INT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE ON UPDATE CASCADE
);

