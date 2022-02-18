package kz.bitlab.m3_ch1.service.impl;

import kz.bitlab.m3_ch1.entities.Sport;
import kz.bitlab.m3_ch1.repository.SportRepository;
import kz.bitlab.m3_ch1.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportServiceImpl implements SportService {

    @Autowired
    private SportRepository sportRepository;

    @Override
    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    @Override
    public Sport getSport(Long id) {
        return sportRepository.getById(id);
    }
}
