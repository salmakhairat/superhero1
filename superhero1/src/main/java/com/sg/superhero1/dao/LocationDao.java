package com.sg.superhero1.dao;

import com.sg.superhero1.dto.Location;

import java.util.List;

public interface LocationDao {
    Location createNewLocation(Location location);
    List<Location> getAllLocations();
    Location findLocationById(int id);
    void updateLocation(Location location);
    void deleteLocationById(int id);
}
