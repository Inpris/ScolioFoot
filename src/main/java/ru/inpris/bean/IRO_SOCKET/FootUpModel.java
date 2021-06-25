package ru.inpris.bean.IRO_SOCKET;

import java.io.Serializable;

public class FootUpModel implements Serializable {
    private Integer id, circumferences, reduction;

    public FootUpModel() {
    }

    public FootUpModel(Integer id, Integer circumferences, Integer reduction) {
        this.id = id;
        this.circumferences = circumferences;
        this.reduction = reduction;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCircumferences() { return circumferences; }
    public void setCircumferences(Integer circumferences) {
        this.circumferences = circumferences;
    }

    public Integer getReduction() {
        return reduction;
    }
    public void setReduction(Integer reduction) {
        this.reduction = reduction;
    }
}
