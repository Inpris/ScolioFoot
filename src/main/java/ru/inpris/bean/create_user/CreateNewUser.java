package ru.inpris.bean.create_user;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arhimag on 06.06.19.
 */
@ManagedBean
@ViewScoped
public class CreateNewUser implements Serializable {
    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;

    private String id = "";
    public String username = "";
    private String password = "";
    private String REPEATpassword = "";
    private Integer role = 0;

    private List<RoleUpModel> roleList = new ArrayList<>();

    @PostConstruct
    public void init() {

        try (Connection connect = ds.getConnection();
             PreparedStatement stm = connect.prepareStatement("select id, role from foot.adm_role order by id")) {
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                    roleList.add(new RoleUpModel(res.getInt(1), res.getString(2)));
                    System.out.println(res.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("mistake role load");
        }

    }

    public List<RoleUpModel> getRoleList() {return roleList;}
    public void setRoleList(List<RoleUpModel> roleList) {
        this.roleList = roleList;
    }

    public Integer getRole() {return role;}
    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {this.id= id;}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getREPEATpassword() {
        return REPEATpassword;
    }
    public void setREPEATpassword(String REPEATpassword) {
        this.REPEATpassword = REPEATpassword;
    }

    public void newUser(String id, String password, String username, Integer role) {
        try (Connection connect = ds.getConnection()) {

            PreparedStatement stm = connect.prepareStatement("INSERT into foot.adm_user (login, name, password, status, role) values (?, ?, ?, ?, ?)");
            stm.setString(1, id);
            stm.setString(2, username);
            stm.setString(3, password);
            stm.setString(4, "active");
            stm.setInt(5, role);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toLogin(){
        return "/faces/login.xhtml";
    }

    public void save(){
        FacesContext context = FacesContext.getCurrentInstance();
        Boolean NOidentically = true;
        Boolean NoPasswordLength = true;
        try (Connection connect = ds.getConnection();
             PreparedStatement stm = connect.prepareStatement("select name from foot.adm_user where login = ?")) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                if (rs.getString("name")!=null){
                    NOidentically = false;
                }
            }
//            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            NOidentically = false;
        }
        if (password.length()<8){
            NoPasswordLength = false;
        }
        if (NoPasswordLength&NOidentically&(password.equals(REPEATpassword))&!password.equals("")&!username.equals("")&!id.equals("")&(role!=0)){
            newUser(id, password, username, role);
            setPassword(null);
            setREPEATpassword(null);
            setUsername(null);
            setId(null);
            setRole(0);
            context.addMessage(null, new FacesMessage("User created."));

        }else {
            if (!NoPasswordLength) {
                context.addMessage(null, new FacesMessage("Password must be longer than 7 characters."));
            }
            if (!NOidentically) {
                context.addMessage(null, new FacesMessage("A user with this login already exists."));
            }
            if (!password.equals(REPEATpassword)){
                context.addMessage(null, new FacesMessage("Password and repeated password are not equal."));
            }
            if (password.equals("")){
                context.addMessage(null, new FacesMessage("The password has not been entered."));
            }
            if (REPEATpassword.equals("")){
                context.addMessage(null, new FacesMessage("The repeated password has not been entered."));
            }
            if (username.equals("")){
                context.addMessage(null, new FacesMessage("The name has not been entered."));
            }
            if (id.equals("")){
                context.addMessage(null, new FacesMessage("The login has not been entered."));
            }
            if (role==0){
                context.addMessage(null, new FacesMessage("The role of this user has not been entered."));
            }
        }

    }
}
