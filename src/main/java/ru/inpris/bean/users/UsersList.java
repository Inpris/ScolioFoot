package ru.inpris.bean.view_entered_orders;

import ru.inpris.bean.users.UsersUpModel;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Stateless
public class UsersList {
    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    public DataSource ds;

    public void updateUsers(String login, String name, Integer role, String status) {
        try (Connection connect = ds.getConnection()) {

            PreparedStatement stm = connect.prepareStatement("UPDATE foot.adm_user SET name = ?, status = ?, role = ? where login = ?");

            if (login==null){ login=""; }
            stm.setString(4, login);

            if (name==null){ name=""; }
            stm.setString(1, name);

            if (status==null){ status=""; }
            stm.setString(2, status);

            if (role==null){ role=0; }
            stm.setInt(3, role);

            stm.executeUpdate();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Your details have been successfully update."));
        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "An error has occurred.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();
        }
    }

    public void loadUsers(List<UsersUpModel> list) {
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("SELECT login, name, role, status from foot.adm_user");
            ResultSet res = stm.executeQuery();
            while (res.next()){

                list.add(new UsersUpModel (res.getString(1), res.getString(2), res.getInt(3), res.getString(4)) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
