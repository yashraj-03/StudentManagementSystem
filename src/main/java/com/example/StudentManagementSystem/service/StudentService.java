package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.dto.StudentRequestDto;
import com.example.StudentManagementSystem.dto.StudentResponseDto;
import com.example.StudentManagementSystem.model.StudentModel;
import com.example.StudentManagementSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository){
        this.repository=repository;
    }
//    public StudentModel addStudent(StudentModel student){
//        return repository.save(student);
//    }
    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        student.setUniversityRollNo(dto.getUniversityRollNo());

        StudentModel saved = repository.save(student);
        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail(),
                saved.getUniversityRollNo()
        );
    }
    public List<StudentResponseDto> getAllStudents(){
        return repository.findAll()
                .stream()
                .map(s-> new StudentResponseDto(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getEmail(),
                        s.getUniversityRollNo()
                )).toList();
    }
    //update
    public StudentResponseDto updateStudent(String id, StudentRequestDto dto){
        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found"));

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        student.setUniversityRollNo(dto.getUniversityRollNo());

        StudentModel updated = repository.save(student);
        return new StudentResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getAge(),
                updated.getEmail(),
                updated.getUniversityRollNo()
        );
    }

    //delete
    public void deleteStudent(String id){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("No student found"));
        repository.delete(existingStudent);
    }
}
