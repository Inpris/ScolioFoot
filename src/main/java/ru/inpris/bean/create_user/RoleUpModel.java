package ru.inpris.bean.create_user;

import java.io.Serializable;

public class RoleUpModel implements Serializable {

    public Integer id;
    public String roleName;

    public RoleUpModel () {
    }

    public RoleUpModel (Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
