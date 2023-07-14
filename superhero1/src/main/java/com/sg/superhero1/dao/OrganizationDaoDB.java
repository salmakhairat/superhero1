package com.sg.superhero1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizationDaoDB implements OrganizationDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrganizationDaoDB (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
