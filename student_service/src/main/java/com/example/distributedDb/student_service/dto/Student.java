package com.example.distributedDb.student_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    int id;
    String email;
    String firstName;
    String lastName;
}
