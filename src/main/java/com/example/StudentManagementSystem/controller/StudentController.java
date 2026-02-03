package com.example.StudentManagementSystem.controller;
import com.example.StudentManagementSystem.dto.StudentRequestDto;
import com.example.StudentManagementSystem.dto.StudentResponseDto;
import com.example.StudentManagementSystem.model.StudentModel;
import com.example.StudentManagementSystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/addStudent")
    public StudentResponseDto addStudent(@Valid @RequestBody StudentRequestDto student){
        return service.addStudent(student);
    }
    @GetMapping("/students")
    public List<StudentResponseDto> getStudent(){
        return service.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(@PathVariable String id, @RequestBody StudentRequestDto student){
        return service.updateStudent(id, student);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
    }
}
