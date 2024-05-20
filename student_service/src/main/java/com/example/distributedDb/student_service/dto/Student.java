package com.example.distributedDb.student_service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    int id;

    @Column(name = "email")
    String email;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;
}
