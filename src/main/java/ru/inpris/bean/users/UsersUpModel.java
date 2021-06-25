package ru.inpris.bean.users;

import java.io.Serializable;
import java.util.Date;

public class UsersUpModel implements Serializable {

    String login, status, name;
    Integer role;

    public UsersUpModel() {
    }

    public UsersUpModel(String login, String name, Integer role, String status) {
        this.login = login;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}
