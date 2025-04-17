package com.example.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Student;
import com.example.jpa.entity.Student.Grade;

@SpringBootTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void insertTest() {

        Student student = Student.builder()
                .name("홍길동")
                .grade(Grade.JUNIOR)
                .gender("M")
                .build();

        // insert
        studentRepository.save(student);
    }

    @Test
    public void updateTest() {

        Student student = studentRepository.findById(1L).get();
        student.setGrade(Grade.SENIOR);
        // update
        studentRepository.save(student);
    }
}
