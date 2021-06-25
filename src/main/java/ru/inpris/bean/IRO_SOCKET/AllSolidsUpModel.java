package ru.inpris.bean.IRO_SOCKET;

import java.io.Serializable;

public class AllSolidsUpModel implements Serializable {

    public Integer id;
    public String solidsName;

    public AllSolidsUpModel() {
    }

    public AllSolidsUpModel(Integer id, String solidsName) {
        this.id = id;
        this.solidsName = solidsName;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSolidsName() {
        return solidsName;
    }
    public void setSolidsName(String solidsName) {
        this.solidsName = solidsName;
    }

}
