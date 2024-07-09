package com.management.sbrdemo.service;

import com.management.sbrdemo.exception.StudentAlreadyExistsException;
import com.management.sbrdemo.exception.StudentNotFoundException;
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
    public List<Student> getStudents() {
        return studentRepository.findAll(); // Fetch all students from the database
    }

    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExits(student.getEmail()))
            throw new StudentAlreadyExistsException(student.getEmail() + " already exists");
        {
            return studentRepository.save(student);
        }
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(student1 -> {
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setEmail(student.getEmail());
            student1.setDepartment(student.getDepartment());
            return studentRepository.save(student1);
        }).orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id))
            throw new StudentNotFoundException("Student not found");
        {
            studentRepository.deleteById(id);
        }

    }

    private boolean studentAlreadyExits(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

}
