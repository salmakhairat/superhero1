package com.sg.superhero1.dao;

import com.sg.superhero1.dto.Location;
import com.sg.superhero1.dto.Sighting;
import com.sg.superhero1.dto.SuperHeroVillain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
        final String sql = "SELECT location_id, superhero_id, date FROM sightings WHERE sighting_id = ?;";
        return jdbcTemplate.queryForObject(sql, new SightingMapper(), id);
    }

    @Override
    public List<Sighting> getSightingsByDate(Date date) {
        final String sql = "SELECT location_id, superhero_id, date FROM sightings WHERE date = ?;";
        return jdbcTemplate.query(sql, new SightingMapper(), date);
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        final String sql = "INSERT INTO sightings(location_id, superhero_id, date) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sighting.getLocation().getLocationId());
            statement.setInt(2, sighting.getSuperHeroVillain().getSuperHeroVillainId());
            statement.setDate(3, sighting.getDate());
            return statement;
        }, keyHolder);

        sighting.setSighting_id(keyHolder.getKey().intValue());
        return sighting;
    }

    @Override
    public void deleteSightingById(int id) {
        final String sql = "DELETE FROM sightings WHERE sighting_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String sql = "SELECT * FROM sightings;";
        return jdbcTemplate.query(sql, new SightingMapper());
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String sql = "UPDATE sightings SET location_id = ?, superhero_id = ?, date = ? WHERE sighting_id = ?;";
        jdbcTemplate.update(sql,
                sighting.getLocation().getLocationId(),
                sighting.getSuperHeroVillain().getSuperHeroVillainId(),
                sighting.getDate(),
                sighting.getSighting_id());
    }
    public static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            SuperHeroVillainDao superDao = new SuperHeroVillainDaoDB(new JdbcTemplate());
            LocationDao locationDao = new LocationDaoDB(new JdbcTemplate());

            Sighting sighting = new Sighting();

            int superId = rs.getInt("superherovillain_id");
            int locationId = rs.getInt("location_id");
            sighting.setSighting_id(rs.getInt("sighting_id"));
            sighting.setSuperHeroVillain(superDao.getSuperHeroVillainById(superId));
            sighting.setLocation(locationDao.getLocationById(locationId));
            sighting.setDate(rs.getDate("date"));

            return sighting;
        }
    }
}
