package com.example.crudapi.controller;

import com.example.crudapi.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1, "Vedika", "Java"));
        students.add(new Student(2, "Rahul", "Python"));
    }

    // CREATE
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    // READ ALL
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    // READ ONE
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id,
                                 @RequestBody Student student) {

        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(student.getName());
                s.setCourse(student.getCourse());
                return s;
            }
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        students.removeIf(s -> s.getId() == id);
        return "Student Deleted Successfully";
    }
}