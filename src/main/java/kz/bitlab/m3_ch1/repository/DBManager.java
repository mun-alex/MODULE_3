package kz.bitlab.m3_ch1.repository;

import kz.bitlab.m3_ch1.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DBManager implements StudentRepository {

    private static List<Student> studentList = new ArrayList<>();

    static {
        studentList.add(new Student(1L, "Azhar", "2000-04-18", "Shimkent"));
        studentList.add(new Student(2L, "Begarys", "2000-04-18", "Almaty"));
        studentList.add(new Student(3L, "Aisulu", "2000-04-18", "Almaty"));
        studentList.add(new Student(4L, "Beka", "2000-04-18", "Almaty"));
        studentList.add(new Student(5L, "Abzal", "2000-04-18", "Almaty"));
        studentList.add(new Student(6L, "Islam", "2000-04-18", "Almaty"));
    }

    private static Long id = 6L;

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }

    @Override
    public void addStudent(Student student) {
        student.setId(id);
        studentList.add(student);
        id++;
    }

    @Override
    public Student getStudentById(Long id) {
        for (Student student : studentList) {
            if (student.getId() == id) return student;
        }
        return null;
    }
}
