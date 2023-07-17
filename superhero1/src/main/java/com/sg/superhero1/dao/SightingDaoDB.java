package com.sg.superhero1.dao;

import com.sg.superhero1.dto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class SightingDaoDB implements SightingDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SightingDaoDB (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sighting getSightingById(int id) {
        //final String sql = "SELECT location_id, superhero_id, "
        return null;
    }

    @Override
    public List<Sighting> getSightingsByDate(Date date) {
        return null;
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        return null;
    }

    @Override
    public void deleteSightingById(int id) {

    }

    @Override
    public List<Sighting> getAllSightings() {
        return null;
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        return null;
    }
}
