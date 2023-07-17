package com.sg.superhero1.dto;


import java.util.Objects;

public class SuperHeroVillain {
    private int superherovillainId;
    private String name;
    private String description;
    private String superpower;


    public SuperHeroVillain() {
    }

    public SuperHeroVillain(int superherovillainId, String name, String description, String superpower) {
        this.superherovillainId = superherovillainId;
        this.name = name;
        this.description = description;
        this.superpower = superpower;
    }

    public int getSuperHeroVillainId() {
        return superherovillainId;
    }

    public void setSuperHeroVillainId(int superherovillainId) {
        this.superherovillainId = superherovillainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperHeroVillain that = (SuperHeroVillain) o;
        return superherovillainId == that.superherovillainId && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(superpower, that.superpower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(superherovillainId, name, description, superpower);
    }
}

