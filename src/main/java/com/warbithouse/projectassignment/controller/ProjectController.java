package com.warbithouse.projectassignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warbithouse.projectassignment.model.Project;
import com.warbithouse.projectassignment.repository.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "${SPRING_ORIGINS:*}")
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/")
    public ResponseEntity<List<Project>> getProject() {
        return ResponseEntity.ok(projectRepository.findAll());
    }

}
