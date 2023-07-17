package com.sg.superhero1.dao;

import com.sg.superhero1.dto.SuperHeroVillain;

import java.util.List;

public interface SuperHeroVillainDao {
    SuperHeroVillain getSuperHeroVillainById(int superherovillainId);

    List<SuperHeroVillain> getAllSuperHeroVillains();

    SuperHeroVillain create(SuperHeroVillain superheroVillain);

    void update(SuperHeroVillain superheroVillain);

    void delete(int superherovillainId);
}
