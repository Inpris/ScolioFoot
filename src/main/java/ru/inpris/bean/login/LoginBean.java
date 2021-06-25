package ru.inpris.bean.login;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

///**
// * Created by arhimag on 08.05.19.
// */
@Stateless //���������� ������, ������ �������� �� �� stateless, �� � managedBean
@Named
public class LoginBean {
    private String username;
    private String password, lang = "IRO_SOCKET_rus.xhtml";
    private int reg, role = 1;

    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String login (){
        //String result;
        FacesContext  context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        HttpSession se = request.getSession();
        se.setAttribute("user", "null");
        se.setAttribute("isLoggedIn", null);
        se.setAttribute("role", null);
        String impPas;
        if (username==null||password==null){
            if (username==null){
                context.addMessage(null, new FacesMessage("Login not entered."));
            }
            if (password==null){
                context.addMessage(null, new FacesMessage("Pasword not entered."));
            }
            return "/";
        }else {
            String select = "select password, role from foot.adm_user where login = '" + username + "'";
            try (Connection connect = ds.getConnection();
                 PreparedStatement stm = connect.prepareStatement(select)) {
                ResultSet res = stm.executeQuery();
                res.next();
                reg = 0;
                impPas = res.getString("password");
                if (password.equals(impPas)) {
                    HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
                    session.setAttribute("user", getUsername());
                    reg = 1;
                    role = res.getInt("role");
                    session.setAttribute("isLoggedIn", "true");
                    session.setAttribute("role", res.getInt("role"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            if ((reg == 1)&(role == 1)) {
                return "/faces/"+lang;
            } else if ((reg == 1)&(role == 2)){
                return "/faces/allOrders.xhtml";}
            else {
                se.setAttribute("user", "null");
                se.setAttribute("isLoggedIn", null);
                context.addMessage(null, new FacesMessage("Wrong login or password."));
                return "/";
            }
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
                context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }

    public String ENG (){
        lang = "IRO_SOCKET_eng.xhtml";
        return "/faces/login_eng.xhtml";
    }

    public String RUS (){
        lang = "IRO_SOCKET_rus.xhtml";
        return "/faces/login.xhtml";
    }

    public String registration (){
        if (username==null){
            username="";
        }
        if (password==null){
            password="";
        }
        return "/faces/registration.xhtml";
    }
}
