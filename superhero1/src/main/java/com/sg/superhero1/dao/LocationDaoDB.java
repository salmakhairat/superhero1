package com.sg.superhero1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDaoDB implements LocationDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationDaoDB (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
