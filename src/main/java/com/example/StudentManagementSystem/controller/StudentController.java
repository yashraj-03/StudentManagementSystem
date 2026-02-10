package com.example.StudentManagementSystem.controller;
import com.example.StudentManagementSystem.dto.StudentRequestDto;
import com.example.StudentManagementSystem.dto.StudentResponseDto;
import com.example.StudentManagementSystem.model.StudentModel;
import com.example.StudentManagementSystem.service.StudentService;
import com.example.StudentManagementSystem.util.JwtUtil;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
public class StudentController {
    private final StudentService service;
    private final JwtUtil jwtUtil;

    public StudentController(StudentService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }
    private void checkToken(String authHeader){
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            throw new RuntimeException("Invalid token");
        }
        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

    @PostMapping("/addStudent")
    public StudentResponseDto addStudent(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody StudentRequestDto student){
        checkToken(authHeader);
        return service.addStudent(student);
    }
    @GetMapping("/students")
    public List<StudentResponseDto> getStudent(
            @RequestHeader(value="Authorization", required = false) String authHeader
    ){
        checkToken(authHeader);
        return service.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id, @RequestBody StudentRequestDto student){
        checkToken(authHeader);
        return service.updateStudent(id, student);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id){
        checkToken(authHeader);
        service.deleteStudent(id);
    }
}
