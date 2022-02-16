package kz.bitlab.m3_ch1.service;


import kz.bitlab.m3_ch1.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void addStudent(Student student);
    Student getStudentById(Long id);
    void updateStudent(Student student);
    void deleteStudentById(Long id);
}
