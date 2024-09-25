package com.example.SpringSecurityUsingInMemoryDatabase.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringSecurityUsingInMemoryDatabase.Dao.SubjectRepository;
import com.example.SpringSecurityUsingInMemoryDatabase.Modal.Subject;

import java.util.List;

@Service
public class SubjectServiceImp implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}

