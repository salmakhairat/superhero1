package com.sg.superhero1.dao;

import com.sg.superhero1.dto.Sighting;

import java.sql.Date;
import java.util.List;

public interface SightingDao {
    Sighting getSightingById(int id);
    List<Sighting> getSightingsByDate(Date date);
    Sighting addSighting(Sighting sighting);
    void deleteSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting updateSighting(Sighting sighting);

}
