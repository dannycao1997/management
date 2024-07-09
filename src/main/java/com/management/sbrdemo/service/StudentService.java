package com.management.sbrdemo.service;

import com.management.sbrdemo.exception.StudentAlreadyExistsException;
import com.management.sbrdemo.model.Student;
import com.management.sbrdemo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok annotation to generate constructor with required arguments
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExits(student.getEmail()))
            throw new StudentAlreadyExistsException(student.getEmail() + " already exists");
        {
            return studentRepository.save(student);
        }
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll(); // Fetch all students from the database
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

    private boolean studentAlreadyExits(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

}
