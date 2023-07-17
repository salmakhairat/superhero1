package com.sg.superhero1.dao;

import com.sg.superhero1.dto.SuperHeroVillain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class SuperHeroVillainDaoDB implements SuperHeroVillainDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SuperHeroVillainDaoDB (JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public SuperHeroVillain getSuperHeroVillainById(int superherovillainId) {
        final String sql = "SELECT * FROM superherovillain WHERE superherovillain_id = ?";
        List<SuperHeroVillain> superHeroVillains = jdbcTemplate.query(sql, new SuperHeroVillainMapper(), superherovillainId);
        return superHeroVillains.isEmpty() ? null : superHeroVillains.get(0);
    }

    @Override
    public List<SuperHeroVillain> getAllSuperHeroVillains() {
        final String sql = "SELECT * FROM superherovillain";
        return jdbcTemplate.query(sql, new SuperHeroVillainMapper());
    }

    @Override
    public SuperHeroVillain create(SuperHeroVillain superherovillain) {

            final String sql = "INSERT INTO superherovillain( name, description, superpower )" +
                    " VALUES(?,?,?);";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update( ( Connection connection ) ->
            {
                PreparedStatement prepStatement = connection.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS );

                prepStatement.setString( 1, superherovillain.getName() );
                prepStatement.setString( 2, superherovillain.getDescription() );
                prepStatement.setString( 3, superherovillain.getSuperpower() );
                return prepStatement;
            }, keyHolder );

            superherovillain.setSuperHeroVillainId( keyHolder.getKey().intValue() );
            return superherovillain;

    }

    @Override
    public void update(SuperHeroVillain superheroVillain) {
        final String sql = "UPDATE superherovillain SET name = ?, description = ?, superpower = ? WHERE superherovillain_id = ?";
        jdbcTemplate.update(sql, superheroVillain.getName(), superheroVillain.getDescription(), superheroVillain.getSuperpower(), superheroVillain.getSuperHeroVillainId());

    }

    @Override
    public void delete(int superherovillainId) {
        final String sql = "DELETE FROM superherovillain WHERE superherovillain_id = ?";
        jdbcTemplate.update(sql, superherovillainId);
    }

    public static class SuperHeroVillainMapper implements RowMapper<SuperHeroVillain> {
        @Override
        public SuperHeroVillain mapRow(ResultSet rs, int rowNum) throws SQLException {
            int superHeroVillainId = rs.getInt("superherovillain_id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String superpower = rs.getString("superpower");

            return new SuperHeroVillain(superHeroVillainId, name, description, superpower);
        }
    }
}



