package com.warbithouse.projectassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warbithouse.projectassignment.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
