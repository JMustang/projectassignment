package com.warbithouse.projectassignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warbithouse.projectassignment.model.Project;
import com.warbithouse.projectassignment.repository.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        return ResponseEntity.of(projectRepository.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Project> createProject(@RequestBody Project projectDetails) {
        Project project = projectRepository.save(projectDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project projectDetails) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setName(projectDetails.getName());
        return ResponseEntity.ok(projectRepository.save(project));
    }
}
