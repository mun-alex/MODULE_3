package kz.bitlab.m3_ch1.service.impl;

import kz.bitlab.m3_ch1.entities.Student;
import kz.bitlab.m3_ch1.repository.StudentRepository;
import kz.bitlab.m3_ch1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getById(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudentsByCityId(Long id) {
        return studentRepository.findAllByCityId(id);
    }

    @Override
    public List<Student> getAllStudentsByFacultyId(Long id) {
        return studentRepository.findAllByFacultyId(id);
    }

    @Override
    public List<Student> getAllStudentsByCityIdAndfFacultyId(Long cityId, Long facultyId) {
        return studentRepository.findAllByCityIdAndFacultyId(cityId, facultyId);
    }
}
