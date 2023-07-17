package com.sg.superhero1.dao;

import com.sg.superhero1.dto.Organization;
import com.sg.superhero1.dto.SuperHeroVillain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrganizationDaoDB implements OrganizationDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrganizationDaoDB (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM organization WHERE organization_id = ?";
             Organization organization = jdbcTemplate.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
             organization.setMembers(getMembersForOrganization(id));
             return organization;

        } catch(DataAccessException ex) {
            return null;
        }
    }

//     create a helper method to get SuperHeroVillains for an organization:
    private List<SuperHeroVillain> getMembersForOrganization(int id){
        final String SELECT_MEMBERS_FOR_ORGANIZATION = "SELECT s.* FROM superherovillain s "
                + "INNER JOIN superhero_organization so ON so.superhero_id = s.superherovillain_id"
                + "WHERE so.organization_id = ?";
        return jdbcTemplate.query(SELECT_MEMBERS_FOR_ORGANIZATION, new SuperHeroVillainDaoDB.SuperHeroVillainMapper(), id);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organization";
        List<Organization> organizations = jdbcTemplate.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
        addSuperHerosToOrganization(organizations);

        return organizations;
    }

//    a private helper method
    private void addSuperHerosToOrganization(List<Organization> organizations) {
        for (Organization organization: organizations){
            organization.setMembers(getMembersForOrganization(organization.getId()));
        }
    }

    @Override
    @Transactional
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organization "
                + "SET name = ?, description = ?, org_address = ?, contact = ?  WHERE organization_id = ?";
        jdbcTemplate.update(UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact(),
                organization.getId());

//         deleting all the existing bridge table entries for the relationship.
        final String DELETE_ORGANIZATION_SUPERHERO = "DELETE FROM superhero_organization "
                + "WHERE organization_id = ?";
        jdbcTemplate.update(DELETE_ORGANIZATION_SUPERHERO, organization.getId());
        insertOrganizationSuperHero(organization);
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        final String DELETE_ORGANIZATION_SUPERHERO = "DELETE FROM superhero_organization "
                + "WHERE organization_id = ?";
        jdbcTemplate.update(DELETE_ORGANIZATION_SUPERHERO, id);

        final String DELETE_ORGANIZATION = "DELETE FROM organization WHERE organization_id = ?";
        jdbcTemplate.update(DELETE_ORGANIZATION, id);
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO organization(name, description, org_address, contact) VALUES(?,?,?, ?)";
        jdbcTemplate.update(INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);

        insertOrganizationSuperHero(organization);
        return organization;
    }
    private void insertOrganizationSuperHero(Organization organization) {

//         We can create our query String before the loop because it never actually changes; only the data we put into it changes.
        final String INSERT_MEMBER = "INSERT INTO superhero_organization (superhero_id, organization_id) "
                + "VALUES(?,?)";
        for (SuperHeroVillain member : organization.getMembers()){
            jdbcTemplate.update(INSERT_MEMBER, organization.getId(), member.getSuperHeroVillainId());
//            comes from superHeroVillain class
        }
    }


    @Override
    public List<Organization> getAllOrganizationsForSuperhero(int superheroId) {
        final String SELECT_ORGANIZATIONS_FOR_SUPERHERO = "SELECT * FROM organization o "
                + "JOIN superhero_organization so ON o.organization_id = so.organization_id WHERE so.superhero_id = ?";
        List<Organization> organizations = jdbcTemplate.query(SELECT_ORGANIZATIONS_FOR_SUPERHERO,
                new OrganizationMapper(), superheroId);

        addSuperHerosToOrganization(organizations);

        return organizations;
    }


    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("organization_id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("org_address"));
            organization.setContact(rs.getString("contact"));
            return organization;
        }
    }
}
