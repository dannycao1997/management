package com.management.sbrdemo.service;

import com.management.sbrdemo.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{
    @Override
    public Student addStudent(Student student) {
        return null;
    }

    @Override
    public List<Student> getStudents() {
        return null;
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return null;
    }

    @Override
    public Student getStudentById(Long id) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }
}
