package kz.bitlab.m3_ch1.service;


import kz.bitlab.m3_ch1.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    List<Student> getAllStudentsByCityId(Long id);
    List<Student> getAllStudentsByFacultyId(Long id);
    List<Student> getAllStudentsByCityIdAndfFacultyId(Long cityId, Long facultyId);
    void addStudent(Student student);
    Student getStudentById(Long id);
    void updateStudent(Student student);
    void deleteStudentById(Long id);
}
