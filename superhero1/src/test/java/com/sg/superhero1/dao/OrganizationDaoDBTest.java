package com.sg.superhero1.dao;

import com.sg.superhero1.TestApplicationConfiguration;
import com.sg.superhero1.dto.Location;
import com.sg.superhero1.dto.Organization;
import com.sg.superhero1.dto.Sighting;
import com.sg.superhero1.dto.SuperHeroVillain;
import org.apache.tomcat.jni.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class OrganizationDaoDBTest {

    @Autowired
    OrganizationDao orgDao;

    @Autowired
    SuperHeroVillainDao superDao;

    @Autowired
    LocationDao locDao;

    @Autowired
    SightingDao sightDao;



    @BeforeEach
    public void setUp() {
        List<Organization> organizations = orgDao.getAllOrganizations();
        for (Organization organization: organizations){
            orgDao.deleteOrganizationById(organization.getId());
        }

        List<Sighting> sightings = sightDao.getAllSightings();
        for (Sighting sighting: sightings){
            sightDao.deleteSightingById(sighting.getSighting_id());
        }

        List<SuperHeroVillain> superpeople = superDao.getAllSuperpeople();
        for (SuperHeroVillain superperson : superpeople){
            superDao.deleteSuperpersonById(superperson.getId());
        }

        List<Location> locations = locDao.getAllLocations();
        for (Location location : locations){
            locDao.deleteLocationById(location.getId());
        }
    }

    // we need to build a full Organization object in every test.
    @Test
    public void testAddGetOrganization() {

        SuperHeroVillain superhero = new SuperHeroVillain();
        superhero.setName("Test name");
        superhero.setDescription("Test descr");
        superhero.setSuperpower("Test power");
        superhero = superDao.addSuperhero(superhero);

        List<SuperHeroVillain> superheros = new ArrayList<>();
        superheros.add(superhero);

        Organization org = new Organization();
        org.setName("Test");
        org.setDescription("Test");
        org.setAddress("Test");
        org.setContact("Test");
        org.setMembers(superheros);

        org = orgDao.addOrganization(org);

        Organization fromDao = orgDao.getOrganizationById(org.getId());

        assertNotNull(fromDao);
        assertEquals(org, fromDao);
    }

    @Test
    public void testGetAllOrganizations() {

        SuperHeroVillain superhero = new SuperHeroVillain();
        superhero.setName("Test name");
        superhero.setDescription("Test descr");
        superhero.setSuperpower("Test power");
        superhero = superDao.addSuperhero(superhero);

        List<SuperHeroVillain> superheros = new ArrayList<>();
        superheros.add(superhero);

        Organization org1 = new Organization();
        org1.setName("Test1");
        org1.setDescription("Test1");
        org1.setAddress("Test1");
        org1.setContact("Test1");
        org1.setMembers(superheros);
        org1 = orgDao.addOrganization(org1);

        Organization org2 = new Organization();
        org2.setName("Test2");
        org2.setDescription("Test2");
        org2.setAddress("Test2");
        org2.setContact("Test2");
        org2.setMembers(superheros);
        org2 = orgDao.addOrganization(org2);

        List<Organization> organizations = orgDao.getAllOrganizations();
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(org1));
        assertTrue(organizations.contains(org2));
    }

    @Test
    public void testUpdateOrganization() {

        SuperHeroVillain superhero = new SuperHeroVillain();
        superhero.setName("Test name");
        superhero.setDescription("Test descr");
        superhero.setSuperpower("Test power");
        superhero = superDao.addSuperhero(superhero);

        List<SuperHeroVillain> superheros = new ArrayList<>();
        superheros.add(superhero);

        Organization org = new Organization();
        org.setName("Test");
        org.setDescription("Test");
        org.setAddress("Test");
        org.setContact("Test");
        org.setMembers(superheros);
        org = orgDao.addOrganization(org);

        Organization fromDao = orgDao.getOrganizationById(org.getId());

        assertEquals(org, fromDao);

        org.setName("Test updated");

        SuperHeroVillain superhero2 = new SuperHeroVillain();
        superhero2.setName("Test name2");
        superhero2.setDescription("Test descr2");
        superhero2.setSuperpower("Test power2");
        superhero2 = superDao.addSuperhero(superhero2);

        superheros.add(superhero2);

        org.setMembers(superheros);
        orgDao.updateOrganization(org);  //We call "updateOrganization" to save it in the database.

        assertNotEquals(org, fromDao); // the local org does not match the org we previously retrieved from the database.

        fromDao = orgDao.getOrganizationById(org.getId());  //re-retrieve the org from the database

        assertEquals(org, fromDao);
    }

    @Test
    public void testDeleteOrganization() {
        SuperHeroVillain superhero = new SuperHeroVillain();
        superhero.setName("Test name");
        superhero.setDescription("Test descr");
        superhero.setSuperpower("Test power");
        superhero = superDao.addSuperhero(superhero);

        List<SuperHeroVillain> superheros = new ArrayList<>();
        superheros.add(superhero);

        Organization org = new Organization();
        org.setName("Test");
        org.setDescription("Test");
        org.setAddress("Test");
        org.setContact("Test");
        org.setMembers(superheros);
        org = orgDao.addOrganization(org);

        orgDao.deleteOrganizationById(org.getId());

        Organization fromDao = orgDao.getOrganizationById(org.getId());

        assertNull(fromDao);
    }

    @Test
    public void testGetAllOrganizationsForSuperhero() {
        SuperHeroVillain superhero1 = new SuperHeroVillain();
        superhero1.setName("Test name1");
        superhero1.setDescription("Test descr1");
        superhero1.setSuperpower("Test power1");
        superhero1 = superDao.addSuperhero(superhero1);

        SuperHeroVillain superhero2 = new SuperHeroVillain();
        superhero2.setName("Test name2");
        superhero2.setDescription("Test descr2");
        superhero2.setSuperpower("Test power2");
        superhero2 = superDao.addSuperhero(superhero2);

        List<SuperHeroVillain> superheros = new ArrayList<>();
        superheros.add(superhero1);
        superheros.add(superhero2);

        List<SuperHeroVillain> superheros2 = new ArrayList<>();
        superheros2.add(superhero2);

        Organization org = new Organization();
        org.setName("Test");
        org.setDescription("Test");
        org.setAddress("Test");
        org.setContact("Test");
        org.setMembers(superheros);
        org = orgDao.addOrganization(org);

        Organization org2 = new Organization();
        org2.setName("Test2");
        org2.setDescription("Test2");
        org2.setAddress("Test");
        org2.setContact("Test");
        org2.setMembers(superheros2);
        org2 = orgDao.addOrganization(org2);

        Organization org3 = new Organization();
        org3.setName("Test");
        org3.setDescription("Test");
        org3.setAddress("Test");
        org3.setContact("Test");
        org3.setMembers(superheros);
        org3 = orgDao.addOrganization(org3);

        List<Organization> organizationsForSuperhero = orgDao.getAllOrganizationsForSuperhero(superhero1.getId());
        assertEquals(2, organizationsForSuperhero.size());
        assertTrue(organizationsForSuperhero.contains(org));
        assertTrue(organizationsForSuperhero.contains(org3));
        assertFalse(organizationsForSuperhero.contains(org2));
    }


}
