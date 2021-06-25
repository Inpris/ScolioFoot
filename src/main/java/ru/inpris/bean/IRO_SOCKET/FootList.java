package ru.inpris.bean.IRO_SOCKET;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Stateless
//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class FootList {
    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;

    public void loadNewFoot(Integer heightToTibialTuberosity, String status, String login, String firstName, String lastName, String side, java.sql.Date dissue, Integer solids,
//                             String smo, String company, String companyContactName, String companyContactPhone, String companyContactEMail, String companyContactFax, Integer weight, String modifications, String allard, String allardSide, Integer shippingOptions, Boolean returnImpressions, String chafeOptions,
                             Integer footLength, Integer first, Integer second, Integer third, Integer fourth, Integer fifth, Integer sixth, Integer seventh, Integer eighth, Integer ninth, java.sql.Date dconsultation, String mark, String address, String diagnosis, String notes, String patronymic) {
//                            Boolean toeWalking, Boolean openHeel, Boolean dorsalChips, Boolean nonSkidSole, Boolean houdiniStraps, Boolean carbonFiberFootplates, Boolean springSteel, Boolean adjustalift, Boolean shoesPink, Boolean shoesBlue, Boolean shoesBlack, Boolean smoSocks
        if (haveSavedData(login)){
            deleteSaved(login);}
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("INSERT into foot.p_saved (id, login, first_name, last_name, side, dissue, insDate, " +
                    "solids, dconsultation, foot_length, \"first\", \"second\", \"third\", "+
                    "\"fourth\", \"fifth\", \"sixth\", \"seventh\", \"eighth\", \"ninth\", status, height_to_tibial_tuberosity, patronymic, diagnosis, notes, marks, address" +
              ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stm.setInt(1, getSavedId());
            stm.setString(2, login);
            if (firstName==null){ firstName=""; }
            stm.setString(3, firstName);

            if (lastName==null){ lastName=""; }
            stm.setString(4, lastName);

            if (side==null){ side=""; }
            stm.setString(5, side);

            if(dissue==null){ dissue = new java.sql.Date(1111-10-10); }
            stm.setDate(6, dissue);

            Date date = new Date(System.currentTimeMillis());
            stm.setDate(7, date);

            if (solids==null){solids=-1;}
            stm.setInt(8, solids);

            if (dconsultation==null){dconsultation = new java.sql.Date(1111-10-10);}
            stm.setDate(9, dconsultation);

            if (footLength==null){footLength=-1;}
            stm.setInt(10, footLength);

            if (first==null){first=-1;}
            stm.setInt(11, first);

            if (second==null){second=-1;}
            stm.setInt(12, second);

            if (third==null){third=-1;}
            stm.setInt(13, third);

            if (fourth==null){fourth=-1;}
            stm.setInt(14, fourth);

            if (fifth==null){fifth=-1;}
            stm.setInt(15, fifth);

            if (sixth==null){sixth=-1;}
            stm.setInt(16, sixth);

            if (seventh==null){seventh=-1;}
            stm.setInt(17, seventh);

            if (eighth==null){eighth=-1;}
            stm.setInt(18, eighth);

            if (ninth==null){ninth=-1;}
            stm.setInt(19, ninth);

            if (status==null){status="";}
            stm.setString(20, status);

            if (heightToTibialTuberosity==null){heightToTibialTuberosity=-1;}
            stm.setInt(21, heightToTibialTuberosity);

            if (patronymic==null){ patronymic=""; }
            stm.setString(22, patronymic);

            if (diagnosis==null){ diagnosis=""; }
            stm.setString(23, diagnosis);

            if (notes==null){ notes=""; }
            stm.setString(24, notes);

            if (mark==null){ mark=""; }
            stm.setString(25, mark);

            if (address==null){ address=""; }
            stm.setString(26, address);

            stm.executeUpdate();

        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "An error has occurred.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();
        }
    }

    public void loadSolidsList(List<AllSolidsUpModel> list, String lang) {
        try (Connection connect = ds.getConnection();
             PreparedStatement stm = connect.prepareStatement("select id, solids_"+lang+", instock from foot.sp_solids order by id")) {
            ResultSet res = stm.executeQuery();
            int i = 1;
            while (res.next()) {
                if (res.getBoolean(3)) {
                    list.add(new AllSolidsUpModel(i, res.getString(2)));
                    i=i+1;
                    System.out.println(res.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("mistake solids load");
        }
    }

    private void deleteSaved (String login){
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("DELETE from foot.p_saved WHERE login = ? and status = ?");
            stm.setString(1, login);
            stm.setString(2, "edited");
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Boolean haveSavedData (String login){
        Boolean hsd = false;
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("SELECT id, first_name, last_name, side, dconsultation, address, " +
                    "diagnosis, dissue, foot_length, first, second, third, " +
                    "fourth, fifth, sixth, seventh, eighth, ninth, status, height_to_tibial_tuberosity, marks, notes, patronymic" +
                    " FROM foot.p_saved WHERE status = 'edited' and login = '" + login +"'");
            ResultSet res = stm.executeQuery();
            res.next();
            if (res.getString("id")!=null){hsd = true;}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hsd;
    }

    private int getSavedId (){
        int id = 1;
        try (Connection connect = ds.getConnection()) {

            PreparedStatement stm = connect.prepareStatement("SELECT MAX(id) FROM foot.p_saved");
            ResultSet res = stm.executeQuery();
            res.next();

            id = res.getInt(1) + 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return id;
        }
        return id;
    }
}
