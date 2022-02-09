package kz.bitlab.m3_ch1.service;

import kz.bitlab.m3_ch1.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static List<Student> studentList = new ArrayList<>();

    static {
        studentList.add(new Student(1L, "Begarys", "2000-04-18", "Almaty"));
        studentList.add(new Student(2L, "Aisulu", "2000-04-18", "Almaty"));
        studentList.add(new Student(3L, "Beka", "2000-04-18", "Almaty"));
        studentList.add(new Student(4L, "Abzal", "2000-04-18", "Almaty"));
        studentList.add(new Student(5L, "Islam", "2000-04-18", "Almaty"));
    }

    private static Long id = 6L;

    public static List<Student> getAllStudents() {
        return studentList;
    }

    public static void addStudent(Student student) {
        student.setId(id);
        studentList.add(student);
        id++;
    }

    public static Student getStudentById(Long id) {
        for (Student student : studentList) {
            if (student.getId() == id) return student;
        }
        return null;
    }
}
