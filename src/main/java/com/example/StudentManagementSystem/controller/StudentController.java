package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.model.StudentModel;
import com.example.StudentManagementSystem.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/addStudent")
    public StudentModel addStudent(@RequestBody StudentModel student){
        return service.addStudent(student);
    }
}
