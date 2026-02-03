package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestDto {
    @Size(min=3,max=20,message="name should be between 3 and 20 characters long")
    @NotBlank(message = "name must not be blank")
    private String name;
    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;
}
