package com.eltro.DAPI.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student>GetStudents(){
        return studentRepository.findAll();
	
	}

    public void addNewStudent(Student student) {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentEmail.isPresent()){
            throw new IllegalStateException("Email Taken");
        }

        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long id) {
        boolean exist = studentRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Student doesnt exist!");
        }

        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student doesnt exist"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentEmail = studentRepository.findStudentByEmail(student.getEmail());
            if(studentEmail.isPresent()){
                throw new IllegalStateException("Email Taken!");
            }
            student.setEmail(email);
        }
    
    }

}
