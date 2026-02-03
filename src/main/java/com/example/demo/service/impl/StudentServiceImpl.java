package com.example.demo.service.impl;

import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students
            .stream()
            .map(student -> modelMapper.map(student, StudentDTO.class))
            .toList();
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository
            .findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("student not found with ID: " + id)
            );
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO createNewStudent(
        AddStudentRequestDto addStudentRequestDto
    ) {
        Student newStudent = modelMapper.map(
            addStudentRequestDto,
            Student.class
        );
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException(
                "student does not exists with id: " + id
            );
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(
        Long id,
        AddStudentRequestDto addStudentRequestDto
    ) {
        Student student = studentRepository
            .findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Student not found with id: " + id)
            );
        modelMapper.map(addStudentRequestDto, student);
        studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO patchStudentById(Long id, Map<String, Object> updates) {
        Student student = studentRepository
            .findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Student not found with id: " + id)
            );
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    student.setName((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Updating " + key + " is not allowed."
                    );
            }
        });
        studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }
}
