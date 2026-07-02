package com.example.crudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crudapi.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}