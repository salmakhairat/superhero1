package com.sg.superhero1.dto;


import java.sql.Date;

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
}
