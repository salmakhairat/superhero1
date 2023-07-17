/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.superhero1.dao;

import com.sg.superhero1.dto.SuperPower;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author marya
 */
@SpringBootTest
public class SuperPowerDaoDBTest {

    @Autowired
    SuperPowerDao superDao;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetAllSuperpowers() {

        SuperPower power = new SuperPower();
        power.setSuperpowerName("Teleport");
        superDao.addSuperPower(power);

        SuperPower power2 = new SuperPower();
        power.setSuperpowerName("Teleport too");
        superDao.addSuperPower(power2);

        SuperPower power3 = new SuperPower();
        power.setSuperpowerName("Teleport also");
        superDao.addSuperPower(power3);

        List<SuperPower> allSuperPowers = superDao.getAllSuperPowers();
        Assertions.assertEquals(3, allSuperPowers.size());

        Assertions.assertTrue(allSuperPowers.contains(power));

        Assertions.assertTrue(allSuperPowers.contains(power2));

        Assertions.assertTrue(allSuperPowers.contains(power3));
    }

    @Test
    public void testGetSuperpowerById() {
        SuperPower power = new SuperPower();
        power.setSuperpowerName("Teleport");
        superDao.addSuperPower(power);

        SuperPower added = superDao.addSuperPower(power);
        int id = added.getSuperpowerId();

        SuperPower fromDao = superDao.getSuperPowerById(id);

        assertEquals(added, fromDao);
    }

    @Test
    public void testUpdateDeleteSuperpower() {
        SuperPower power = new SuperPower();
        power.setSuperpowerName("Teleport");
        superDao.addSuperPower(power);

        SuperPower added = superDao.addSuperPower(power);
        int id = added.getSuperpowerId();

        SuperPower power2 = new SuperPower();
        power.setSuperpowerName("Teleport too");

        superDao.updateSuperPower(power2);
        SuperPower updated = superDao.getSuperPowerById(id);

        assertEquals(power2, updated);
        
        superDao.deleteSuperPowerById(id);
        Assertions.assertNull(added);
    }

}
