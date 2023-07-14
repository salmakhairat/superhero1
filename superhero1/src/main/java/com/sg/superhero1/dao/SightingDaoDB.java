package com.sg.superhero1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SightingDaoDB implements SightingDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SightingDaoDB (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
