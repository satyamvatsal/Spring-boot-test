package com.example.demo.service;

import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDTO;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);

    StudentDTO createNewStudent(AddStudentRequestDto student);

    void deleteStudentById(Long id);

    StudentDTO updateStudent(Long id,AddStudentRequestDto student);

    StudentDTO patchStudentById(Long id, Map<String,Object> updates);
}
