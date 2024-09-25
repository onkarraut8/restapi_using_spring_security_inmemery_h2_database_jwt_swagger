package com.example.SpringSecurityUsingInMemoryDatabase.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringSecurityUsingInMemoryDatabase.Modal.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
