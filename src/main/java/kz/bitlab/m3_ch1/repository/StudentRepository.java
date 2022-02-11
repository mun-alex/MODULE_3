package kz.bitlab.m3_ch1.repository;

import kz.bitlab.m3_ch1.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudents();
    void addStudent(Student student);
    Student getStudentById(Long id);
}
