package com.example.StudentManagementSystem.controller;
import com.example.StudentManagementSystem.model.StudentModel;
import com.example.StudentManagementSystem.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @GetMapping("/students")
    public List<StudentModel> getStudent(){
        return service.getStudents();
    }

    @PutMapping("/update/{id}")
    public StudentModel updateStudent(@PathVariable String id, @RequestBody StudentModel student){
        return service.updateStudent(id, student);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
    }
}
