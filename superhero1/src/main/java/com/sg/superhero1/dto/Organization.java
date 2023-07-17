package com.sg.superhero1.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {

    private int id;
    private String name;
    private String description;
    private String address;
    private String contact;

    // https://academy.engagelms.com/mod/page/view.php?id=151992&forceview=1
    // We use composition to represent many-to-many relationships in Java
    // we put a List of one object inside another object.
    private List<SuperHeroVillain> members = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<SuperHeroVillain> getMembers() {
        return members;
    }

    public void setMembers(List<SuperHeroVillain> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!address.equals(that.address)) return false;
        if (!Objects.equals(contact, that.contact)) return false;
        return members.equals(that.members);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + address.hashCode();
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + members.hashCode();
        return result;
    }
}
