package com.sg.superhero1.dao;

import com.sg.superhero1.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class LocationDaoDB implements LocationDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    @Transactional
    public Location createNewLocation(Location location) {
        final String sql = "INSERT INTO Location(name, description, address, latitude, longitude) VALUES(?,?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationId(newId);
        return location;
    }


    @Override
    public List<Location> getAllLocations() {
        final String sql = "SELECT * FROM location";
        return jdbcTemplate.query(sql, new LocationMapper());
    }

    @Override
    public Location getLocationById(int id) {
        try {
            final String sql = "SELECT * FROM location WHERE locationId = ?;";
            Location location = jdbcTemplate.queryForObject(sql, new LocationMapper(), id);
            return location;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateLocation(Location location) {
        final String sql = "UPDATE location SET "
                + "name = ?,"
                + "description = ?,"
                + "address = ?,"
                + "latitude = ?"
                + "longitude = ?"
                + "WHERE locationId = ?";
        jdbcTemplate.update(sql,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
    }

    @Override
    @Transactional
    public void deleteLocationById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE locationId = ?";
        jdbcTemplate.update(DELETE_SIGHTING, id);

        final String DELETE_LOCATION = "DELETE FROM location WHERE locationID = ?";
        jdbcTemplate.update(DELETE_LOCATION, id);
    }

    class LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();

            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setLatitude(rs.getString("latitude"));
            location.setLongitude(rs.getString("longitude"));
            return location;
        }
    }
}

