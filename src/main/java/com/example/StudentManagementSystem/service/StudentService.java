package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.model.StudentModel;
import com.example.StudentManagementSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository){
        this.repository=repository;
    }
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);
    }
}
