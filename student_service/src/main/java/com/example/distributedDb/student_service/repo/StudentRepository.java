package com.example.distributedDb.student_service.repo;

import com.example.distributedDb.student_service.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
