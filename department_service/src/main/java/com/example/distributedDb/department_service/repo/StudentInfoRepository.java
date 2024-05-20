package com.example.distributedDb.department_service.repo;

import com.example.distributedDb.department_service.dto.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer> {
}
