package com.sg.superhero1.dao;

import com.sg.superhero1.dto.SuperPower;
import java.util.List;

public interface SuperPowerDao {

    SuperPower getSuperPowerById(int id);

    List<SuperPower> getAllSuperPowers();

    SuperPower addSuperPower(SuperPower superpower);

    void updateSuperPower(SuperPower superpower);

    void deleteSuperPowerById(int id);
}
