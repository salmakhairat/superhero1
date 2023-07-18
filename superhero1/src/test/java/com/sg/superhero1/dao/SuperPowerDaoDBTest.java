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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
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

    /**
     *
     */
    @BeforeEach
    public void setUp() {
        List<SuperPower> superpowers = superDao.getAllSuperPowers();
        for (SuperPower superpower : superpowers) {
            superDao.deleteSuperPowerById(superpower.getSuperpowerId());
        }
    }
    
    @AfterEach
    public void tearDown() {
        List<SuperPower> superpowers = superDao.getAllSuperPowers();
        for (SuperPower superpower : superpowers) {
            superDao.deleteSuperPowerById(superpower.getSuperpowerId());
        }
    }
    
    @Test
    public void testAddGetAllSuperpowers() {
        
        SuperPower power = new SuperPower();
        power.setSuperpowerId(1);
        power.setSuperpowerName("Teleport");
        superDao.addSuperPower(power);
        
        List<SuperPower> allSuperPowers = superDao.getAllSuperPowers();
        Assertions.assertEquals(1, allSuperPowers.size());
        
    }
    
    @Test
    public void testGetSuperpowerById() {
        SuperPower power = new SuperPower();
        power.setSuperpowerId(2);
        power.setSuperpowerName("Teleport");
        
        SuperPower added = superDao.addSuperPower(power);
        
        SuperPower fromDao = superDao.getSuperPowerById(added.getSuperpowerId());
        
        assertEquals(added, fromDao);
    }
    
    @Test
    public void testUpdateDeleteSuperpower() {
        SuperPower power = new SuperPower();
        power.setSuperpowerId(3);
        power.setSuperpowerName("Teleport");
        
        SuperPower added = superDao.addSuperPower(power);
        int id = added.getSuperpowerId();
        
        SuperPower power2 = new SuperPower();
        power.setSuperpowerId(3);
        power.setSuperpowerName("TeleportToo");
        
        superDao.updateSuperPower(power2);
        SuperPower updated = superDao.getSuperPowerById(id);
        
        assertNotEquals(added, updated);
        
        superDao.deleteSuperPowerById(updated.getSuperpowerId());
        Assertions.assertNull(superDao.getSuperPowerById(id));
    }
    
}
