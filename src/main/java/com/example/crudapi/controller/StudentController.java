package com.example.crudapi.controller;

import com.example.crudapi.model.Student;
import com.example.crudapi.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    // CREATE
    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return repository.save(student);
    }

    // READ ALL
    @GetMapping
    public List<Student> getStudents() {
        return repository.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id,
                                 @RequestBody Student student) {

        Student existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existingStudent.setName(student.getName());
        existingStudent.setCourse(student.getCourse());

        return repository.save(existingStudent);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {

        repository.deleteById(id);

        return "Student Deleted Successfully";
    }
}