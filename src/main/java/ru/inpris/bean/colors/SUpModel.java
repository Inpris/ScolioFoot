package ru.inpris.bean.colors;

import java.io.Serializable;

public class SUpModel implements Serializable{

    public String solidsName;
    public Boolean isActive;

    public SUpModel() {
    }

    public SUpModel(String solidsName, Boolean isActive) {
        this.solidsName = solidsName;
        this.isActive = isActive;
    }

    public String getSolidsName() {
        return solidsName;
    }
    public void setSolidsName(String solidsName) {
        this.solidsName = solidsName;
    }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
