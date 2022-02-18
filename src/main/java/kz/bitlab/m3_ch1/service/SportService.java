package kz.bitlab.m3_ch1.service;

import kz.bitlab.m3_ch1.entities.Sport;

import java.util.List;

public interface SportService {
    List<Sport> getAllSports();
    Sport getSport(Long id);
}
