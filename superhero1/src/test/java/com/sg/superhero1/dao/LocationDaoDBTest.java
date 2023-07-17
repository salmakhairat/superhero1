package com.sg.superhero1.dao;

import com.sg.superhero1.TestApplicationConfiguration;
import com.sg.superhero1.dto.Location;
import com.sg.superhero1.dto.Organization;
import com.sg.superhero1.dto.Sighting;
import com.sg.superhero1.dto.SuperHeroVillain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class LocationDaoDBTest {
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
     //deletes all entries of each table in the test database so the test can test properly
        List<Location> locations = locDao.getAllLocations();
        for (Location location : locations) {
            locDao.deleteLocationById(location.getLocationId());
        }
    }

    @Test
    @DisplayName("Create a new game")
    public void testAddGetLocation() {
        Location location = new Location();
        location.setLocationId(1);
        location.setName("New York");
        location.setDescription("Eating a hot dog");
        location.setAddress("123 Times Square Ave");
        location.setLatitude("15N");
        location.setLongitude("30W");
        location = locDao.createNewLocation(location);

        //get the location
        Location fromDao = locDao.getLocationById(location.getLocationId());

        //assert that the location created is equal to the one received
        assertEquals(location, fromDao);
    }

    @Test
    public void testGetAllLocations() {
        Location location1 = new Location();
        location1.setLocationId(2);
        location1.setName("California");
        location1.setDescription("Surfing a wave");
        location1.setAddress("456 Newport Blvd");
        location1.setLatitude("35N");
        location1.setLongitude("40W");
        location1 = locDao.createNewLocation(location1);

        Location location2 = new Location();
        location2.setLocationId(3);
        location2.setName("Minnesota");
        location2.setDescription("Picking apples");
        location2.setAddress("789 Minneapolis St");
        location2.setLatitude("20N");
        location2.setLongitude("15W");
        location2 = locDao.createNewLocation(location2);

        List<Location> locations = locDao.getAllLocations();

        assertEquals(2, locations.size());
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location2));
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setLocationId(3);
        location.setName("Test Location");
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location.setLatitude("TestLatitude");
        location.setLongitude("TestLongitude");
        location = locDao.createNewLocation(location);

        //get the location
        Location fromDao = locDao.getLocationById(location.getLocationId());

        //assert the location is equal to the one created
        assertEquals(location, fromDao);

        //set a new location name
        location.setName("Another Test Location");

        //update the location
        locDao.updateLocation(location);

        //assert the new location is not equal to the fromDao location anymore
        assertNotEquals(location, fromDao);

        //change the fromDao location to the new location
        fromDao = locDao.getLocationById(location.getLocationId());

        //assert the two locations are equal again
        assertEquals(location, fromDao);
    }

    @Test
    public void testDeleteLocation() {
        Location location = new Location();
        location.setLocationId(3);
        location.setName("Test Location");
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location.setLatitude("TestLatitude");
        location.setLongitude("TestLongitude");
        location = locDao.createNewLocation(location);

        //get the location
        Location fromDao = locDao.getLocationById(location.getLocationId());

        //assert that the location we got is the same as the one created
        assertEquals(location, fromDao);

        //delete the location
        locDao.deleteLocationById(location.getLocationId());

        //get the new ID and assert that the location is now null
        fromDao = locDao.getLocationById(location.getLocationId());
        assertNull(fromDao);

//        Sighting sighting = new Sighting();
//        sighting.setSighting_id(3);
//        sighting.setLocation(location.getLocationId());
//        sighting.setSuperHeroVillain("Test SuperHero Villain");
//        sighting.setDate(Date.valueOf("2000-10-19"));



    }




}
