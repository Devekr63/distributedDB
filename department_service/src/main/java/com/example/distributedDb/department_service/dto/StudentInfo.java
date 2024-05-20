package com.example.distributedDb.department_service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "students_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentInfo {
    @Column(name = "student_id")
    @Id
    int studentId;

    @Column(name = "depart_id")
    int departId;

    @Column(name = "enrollment_date")
    Date enrollmentDate;
}
