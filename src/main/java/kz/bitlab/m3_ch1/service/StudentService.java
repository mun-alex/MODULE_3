package kz.bitlab.m3_ch1.service;


import kz.bitlab.m3_ch1.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void addStudent(Student student);
    Student getStudentById(Long id);
}
