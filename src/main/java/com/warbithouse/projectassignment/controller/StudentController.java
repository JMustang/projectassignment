package com.warbithouse.projectassignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warbithouse.projectassignment.model.Project;
import com.warbithouse.projectassignment.model.Student;
import com.warbithouse.projectassignment.repository.ProjectRepository;
import com.warbithouse.projectassignment.repository.StudentRepository;

@RestController
@CrossOrigin(origins = "${SPRING_ORIGINS:*}")
@RequestMapping("/students")
public class StudentController {
    private static int maxProjectPerStudent = 3;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    public Student retrieveStudentById(int id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new ExpressionException("❌ Student with id " + id + " is not found!"));
    }

    public Project retrieveProjectById(int id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new ExpressionException("❌ Project with id " + id + " is not found!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return ResponseEntity.of(studentRepository.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student studentDetails) {
        Student student = studentRepository.save(studentDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Student student = retrieveStudentById(id);
        student.setName(studentDetails.getName());
        student.setAverage(studentDetails.getAverage());
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        Student student = retrieveStudentById(id);
        studentRepository.delete(student);
        return ResponseEntity.ok("Student deleted Successfully!");
    }

    @GetMapping("/max-project")
    public ResponseEntity<Integer> getMaxProjectPerStudent() {
        return ResponseEntity.ok(maxProjectPerStudent);
    }

    @PutMapping("/max-project")
    public ResponseEntity<Integer> updateMaxProjectPerStudent(@RequestBody int maximumNumber) {
        maxProjectPerStudent = maximumNumber;
        return ResponseEntity.ok(maximumNumber);
    }
}
