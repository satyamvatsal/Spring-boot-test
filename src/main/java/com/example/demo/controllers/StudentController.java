package com.example.demo.controllers;


import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudent() {
        return studentService.getAllStudents();
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid AddStudentRequestDto student) {
        StudentDTO newStudent = studentService.createNewStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody @Valid AddStudentRequestDto student ) {
        return ResponseEntity.ok(studentService.updateStudent(id,student));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> patchStudent(@PathVariable Long id, @RequestBody Map<String,Object> updates) {
        return ResponseEntity.ok(studentService.patchStudentById(id,updates));
    }
}
