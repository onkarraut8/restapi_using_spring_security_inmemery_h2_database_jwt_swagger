package com.example.SpringSecurityUsingInMemoryDatabase.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringSecurityUsingInMemoryDatabase.Modal.Subject;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
