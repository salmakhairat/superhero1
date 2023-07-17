package com.sg.superhero1.dao;

import com.sg.superhero1.dto.Organization;
import com.sg.superhero1.dto.SuperHeroVillain;

import java.util.List;

public interface OrganizationDao {

    public Organization addOrganization(Organization organization);

    public Organization getOrganizationById(int id);

    public List<Organization> getAllOrganizations();

    public void updateOrganization(Organization organization);

    public void deleteOrganizationById(int id);


    public List<Organization> getAllOrganizationsForSuperhero(int superheroId);


}
