package ru.inpris.bean.users;

import org.primefaces.event.RowEditEvent;
import ru.inpris.bean.view_entered_orders.UsersList;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

@ManagedBean (name = "users")
@ViewScoped
public class Users implements Serializable {
    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;
    private String login, name, status;
    private Integer role;
    private List<UsersUpModel> list = new ArrayList<>();

    @EJB
    private UsersList bean;

    @PostConstruct
    public void init() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
//        if (session.getAttribute("user").toString()!=null) {
//            login = session.getAttribute("user").toString();
//        }
        bean.loadUsers(list);
    }

    public String logout(){ return "/faces/login.xhtml"; }

//    public String getLogin() {return login;}
//    public void setLogin(String login) { this.login = login; }
//
//    public String getName() {return name;}
//    public void setName(String name) { this.name = name; }
//
//    public Integer getRole() {return role;}
//    public void setRole(Integer role) { this.role = role; }
//
//    public String getStatus() {return status;}
//    public void setStatus(String status) { this.status = status; }

    public List<UsersUpModel> getList() {return list;}
    public void setList(List<UsersUpModel> list) { this.list = list; }

    public void onRowEdit(RowEditEvent event) {
        bean.updateUsers(((UsersUpModel) event.getObject()).getLogin(), ((UsersUpModel) event.getObject()).getName(), ((UsersUpModel) event.getObject()).getRole(), ((UsersUpModel) event.getObject()).getStatus());
    }

    public void onRowCancel(RowEditEvent event) {
        System.out.println("Cancel_Edit");
    }

    public void delete (String login){
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("DELETE from foot.adm_user WHERE login = ?");
            stm.setString(1, login);
            stm.executeUpdate();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "User has been successfully deleted.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "An error has occurred.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();
        }
    }
}
