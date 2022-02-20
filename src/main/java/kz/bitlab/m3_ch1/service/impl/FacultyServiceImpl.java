package kz.bitlab.m3_ch1.service.impl;

import kz.bitlab.m3_ch1.entities.Faculty;
import kz.bitlab.m3_ch1.repository.FacultyRepository;
import kz.bitlab.m3_ch1.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
}
