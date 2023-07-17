package com.sg.superhero1.dto;


import java.sql.Date;
import java.util.Objects;

public class Sighting {
   private int sighting_id;
   private Location location;
   private SuperHeroVillain superHeroVillain;
   private Date date;

    public Sighting(int sighting_id, Location location, SuperHeroVillain superHeroVillain, Date date) {
        this.sighting_id = sighting_id;
        this.location = location;
        this.superHeroVillain = superHeroVillain;
        this.date = date;
    }

    public Sighting(Location location, SuperHeroVillain superHeroVillain, Date date) {
        this.location = location;
        this.superHeroVillain = superHeroVillain;
        this.date = date;
    }

    public Sighting() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SuperHeroVillain getSuperHeroVillain() {
        return superHeroVillain;
    }

    public void setSuperHeroVillain(SuperHeroVillain superHeroVillain) {
        this.superHeroVillain = superHeroVillain;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSighting_id() {
        return sighting_id;
    }

    public void setSighting_id(int sighting_id) {
        this.sighting_id = sighting_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return sighting_id == sighting.sighting_id && Objects.equals(location, sighting.location) && Objects.equals(superHeroVillain, sighting.superHeroVillain) && Objects.equals(date, sighting.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sighting_id, location, superHeroVillain, date);
    }
}
