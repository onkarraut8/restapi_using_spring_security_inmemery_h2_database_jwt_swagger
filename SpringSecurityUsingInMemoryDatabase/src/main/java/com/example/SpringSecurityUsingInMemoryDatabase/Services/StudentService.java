package com.example.SpringSecurityUsingInMemoryDatabase.Services;

import java.util.List;


import com.example.SpringSecurityUsingInMemoryDatabase.Modal.Student;


public interface StudentService {

	Student createStudent(Student student);

	List<Student> getAllStudents();

}
