package com.sg.superhero1.dto;

import java.util.Objects;

public class SuperPower {
    
    private int superPowerId;
    private String superPowerName;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.superPowerId;
        hash = 53 * hash + Objects.hashCode(this.superPowerName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuperPower other = (SuperPower) obj;
        if (this.superPowerId != other.superPowerId) {
            return false;
        }
        return Objects.equals(this.superPowerName, other.superPowerName);
    }

    public int getSuperpowerId() {
        return superPowerId;
    }

    public void setSuperpowerId(int id) {
        this.superPowerId = id;
    }

    public String getSuperpowerName() {
        return superPowerName;
    }

    public void setSuperpowerName(String name) {
        this.superPowerName = name;
    }
}
