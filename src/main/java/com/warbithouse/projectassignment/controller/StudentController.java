package com.warbithouse.projectassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warbithouse.projectassignment.repository.ProjectRepository;
import com.warbithouse.projectassignment.repository.StudentRepository;

@RestController
@CrossOrigin(origins = "${SPRING_ORIGINS:*}")
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private StudentRepository studentRepository;
}
