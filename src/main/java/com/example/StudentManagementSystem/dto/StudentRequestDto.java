package com.example.StudentManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 5,message = "Age cannot  be greater then 5 ")
    @Max(value = 90, message = "Age cannot be greater than 90")
    private int age;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Min(value = 100, message = "University Roll no. cannot be less than 100")
    private int universityRollNo;

}
