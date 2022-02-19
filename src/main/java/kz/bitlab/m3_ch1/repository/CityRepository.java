package kz.bitlab.m3_ch1.repository;

import kz.bitlab.m3_ch1.entities.City;
import kz.bitlab.m3_ch1.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {
}
