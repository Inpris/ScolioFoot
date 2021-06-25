package ru.inpris.bean.admin;

import ru.inpris.bean.IRO_SOCKET.AllSolidsUpModel;
import ru.inpris.bean.colors.SUpModel;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Stateless
public class AdminList {
    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;

    public void loadAllS(List<AllSolidsUpModel> list) {
        try (Connection connect = ds.getConnection();
             PreparedStatement stm = connect.prepareStatement("select id, solids_rus from foot.sp_solids order by id")) {
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                list.add(new AllSolidsUpModel(res.getInt("id"), res.getString("solids_rus")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("mistake solids load");
        }
    }

    public void updateElName(String login, Integer id, String firstName, String lastName, String patronymic, String diagnosis, String side,
                             Integer solids, Integer footLength, Integer heightToTibialTuberosity, Integer first, Integer second, Integer third,
                             Integer fourth, Integer fifth, Integer sixth, Integer seventh,
                             Integer eighth, Integer ninth, String address, String status,
                             String marks, String notes, Date dconsultation, Date disssue, Date insDate) {
        try (Connection connect = ds.getConnection()) {

            PreparedStatement stm = connect.prepareStatement("UPDATE foot.p_saved SET first_name = ?, last_name = ?, patronymic = ?, diagnosis = ?, side = ?," +
                    "solids = ?, foot_length = ?, height_to_tibial_tuberosity = ?, first = ?, second = ?, third = ?, fourth = ?, fifth = ?, sixth = ?, seventh = ?, eighth = ?, ninth = ?, address = ?, status = ?, " +
                    "marks = ?, notes = ?, dconsultation = ?, dissue = ?, insdate = ?, login = ? WHERE id = ?");

            if (firstName==null){ firstName=""; }
            stm.setString(1, firstName);

            if (lastName==null){ lastName=""; }
            stm.setString(2, lastName);

            if (patronymic==null){ patronymic=""; }
            stm.setString(3, patronymic);

            if (diagnosis==null){ diagnosis=""; }
            stm.setString(4, diagnosis);

            if (side==null){ side=""; }
            stm.setString(5, side);

            if (solids==null){solids=-1;}
            stm.setInt(6, solids);

            if (footLength==null){footLength=-1;}
            stm.setInt(7, footLength);

            if (heightToTibialTuberosity==null){heightToTibialTuberosity=-1;}
            stm.setInt(8, heightToTibialTuberosity);

            if (first==null){first=-1;}
            stm.setInt(9, first);

            if (second==null){second=-1;}
            stm.setInt(10, second);

            if (third==null){third=-1;}
            stm.setInt(11, third);

            if (fourth==null){fourth=-1;}
            stm.setInt(12, fourth);

            if (fifth==null){fifth=-1;}
            stm.setInt(13, fifth);

            if (sixth==null){sixth=-1;}
            stm.setInt(14, sixth);

            if (seventh==null){seventh=-1;}
            stm.setInt(15, seventh);

            if (eighth==null){eighth=-1;}
            stm.setInt(16, eighth);

            if (ninth==null){ninth=-1;}
            stm.setInt(17, ninth);

            if (address==null){address="";}
            stm.setString(18, address);

            if (status==null){status="";}
            stm.setString(19, status);

            if (marks==null){marks="";}
            stm.setString(20, marks);

            if (notes==null){notes="";}
            stm.setString(21, notes);

            if(dconsultation==null){ dconsultation = new java.sql.Date(0000-00-00); }
            stm.setDate(22, dconsultation);

            if(disssue==null){ disssue = new java.sql.Date(0000-00-00); }
            stm.setDate(23, disssue);

            if (insDate==null){insDate=new java.sql.Date(0000-00-00);}
            stm.setDate(24, insDate);

            if (login==null){login="";}
            stm.setString(25, login);

            stm.setInt(26, id);

            stm.executeUpdate();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Your details have been successfully update."));
        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "An error has occurred.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();
        }
    }

    public void loadFoot(List<AdminUpModel> list) {
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("SELECT login, id, first_name, solids, last_name, side, dconsultation, address, " +
                    "diagnosis, dissue, foot_length, first, second, third, " +
                    "fourth, fifth, sixth, seventh, eighth, ninth, status, height_to_tibial_tuberosity, marks, notes, patronymic, insdate" +
                    " FROM foot.p_saved order by id");
            ResultSet res = stm.executeQuery();
            while (res.next()){
                list.add(new AdminUpModel (res.getString("login"), res.getInt("id"), res.getString("first_name"), res.getString("last_name"), res.getString("patronymic"), res.getString("diagnosis"), res.getString("side"),
                        res.getInt("solids"), res.getInt("foot_length"), res.getInt("height_to_tibial_tuberosity"), res.getInt("first"), res.getInt("second"), res.getInt("third"),
                        res.getInt("fourth"), res.getInt("fifth"), res.getInt("sixth"), res.getInt("seventh"), res.getInt("eighth"), res.getInt("ninth"),
                        res.getString("address"), res.getString("status"), res.getString("marks"), res.getString("notes"),
                        res.getDate("dconsultation"), res.getDate("dissue"), res.getDate("insdate")) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
