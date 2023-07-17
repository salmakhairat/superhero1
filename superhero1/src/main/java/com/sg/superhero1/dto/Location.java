package com.sg.superhero1.dto;

import java.util.Objects;

public class Location {
    private int locationId;
    private String name;
    private String description;
    private String address;
    private String latitude;
    private String longitude;

    public int getLocationId() {return locationId;}
    public void setLocationId(int locationId) {this.locationId = locationId;}

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name) && Objects.equals(description, location.description) && Objects.equals(address, location.address) && Objects.equals(latitude, location.latitude) && Objects.equals(longitude, location.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, address, latitude, longitude);
    }


}
