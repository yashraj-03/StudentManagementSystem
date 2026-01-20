package com.example.StudentManagementSystem.repository;


import com.example.StudentManagementSystem.model.StudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentModel, String> {

}
