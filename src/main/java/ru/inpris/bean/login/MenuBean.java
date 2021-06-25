package ru.inpris.bean.login;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by arhimag on 24.05.19.
 */
@ManagedBean (name = "menuBean")
@SessionScoped
public class MenuBean implements Serializable {

    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;

    private String userName = "poteryashka";
    public String CheckSessionAttribute (Integer formName) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession se = request.getSession();
//        if(se.getAttribute("isLoggedIn"))
        Object isLoggedIn = se.getAttribute("isLoggedIn");
        if (isLoggedIn==null){
            userName = "poteryashka";
        }else {
            userName=se.getAttribute("user").toString();
            try (Connection connect = ds.getConnection();
                 PreparedStatement stm = connect.prepareStatement("select 1 from foot.adm_user as a, foot.adm_role_form as b  " +
                         "where a.role = b.role_id and a.login = '" + userName  + "' and b.form_id = " + formName
                 )) {
                ResultSet res = stm.executeQuery();
                res.next();
                if (res.getInt(1)!=1) {
                    userName = "poteryashka";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userName;
    }

    public String getUserName(Integer formName) {
        userName=CheckSessionAttribute(formName);
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
