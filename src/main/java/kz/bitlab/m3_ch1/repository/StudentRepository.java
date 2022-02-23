package kz.bitlab.m3_ch1.repository;

import kz.bitlab.m3_ch1.entities.Faculty;
import kz.bitlab.m3_ch1.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByCityId(Long id);
    List<Student> findAllByFacultyId(Long id);
    List<Student> findAllByCityIdAndFacultyId(Long cityId, Long facultyId);
}
