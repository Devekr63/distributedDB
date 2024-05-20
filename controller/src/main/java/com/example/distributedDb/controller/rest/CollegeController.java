package com.example.distributedDb.controller.rest;

import com.example.distributedDb.department_service.dto.StudentInfo;
import com.example.distributedDb.department_service.repo.StudentInfoRepository;
import com.example.distributedDb.student_service.dto.Student;
import com.example.distributedDb.student_service.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentInfoRepository studentInfoRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        return studentRepository.findById(studentId).get();
    }

    @GetMapping("/students/info")
    public List<StudentInfo> getStudentsInfo(){
        return studentInfoRepository.findAll();
    }
}
