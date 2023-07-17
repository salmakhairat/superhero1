package com.sg.superhero1.dao;

import com.sg.superhero1.dto.SuperPower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SuperPowerDaoDB implements SuperPowerDao {
    
    @Autowired
    JdbcTemplate jdbc;
   
    @Override
    public SuperPower addSuperPower(SuperPower superpower) {
        final String INSERT_SUPERPOWER = "INSERT INTO superpower(superpower_name) VALUES(?)";
        
        jdbc.update(INSERT_SUPERPOWER,
                superpower.getSuperpowerName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setSuperpowerId(newId);
        return superpower;
    }

    @Override
    public SuperPower getSuperPowerById(int id) {

        try{
           
            final String GET_SUPERPOWER_BY_ID = "SELECT * FROM superpower WHERE superpower_id = ?";
            return jdbc.queryForObject(GET_SUPERPOWER_BY_ID , new SuperPowerMapper(), id);
        }
        catch (DataAccessException ex){
            return null;
        }
    }

//    //retrieve all superpowers
    @Override
    public List<SuperPower> getAllSuperPowers() {
        String sql = "SELECT * FROM superpower;";
        return jdbc.query(sql, new SuperPowerMapper());
    }

//    //updates superpower in database
    @Override
    public void updateSuperPower(SuperPower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE superpower SET superpower_name = ? WHERE superpower_id = ?";
        jdbc.update(UPDATE_SUPERPOWER, superpower.getSuperpowerName(), superpower.getSuperpowerId());
    }

    @Override
    @Transactional
    //delete superpower based on id
    public void deleteSuperPowerById(int id) {
        final String DELETE_SUPERPOWER = "DELETE FROM superpower WHERE superpower_id = ?;";
        jdbc.update(DELETE_SUPERPOWER, id);
    }

    
    public class SuperPowerMapper implements RowMapper<SuperPower>{

    @Override
    public SuperPower mapRow(ResultSet rs, int rowNum) throws SQLException {
        SuperPower superpower = new SuperPower();
        superpower.setSuperpowerId(rs.getInt("superpower_id"));
        superpower.setSuperpowerName(rs.getString("superpower_name"));
        
        return superpower;
    }
    
}

}
