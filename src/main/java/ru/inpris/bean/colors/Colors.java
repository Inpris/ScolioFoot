package ru.inpris.bean.colors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class Colors implements Serializable {
    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;
    public List<SUpModel> solidsList = new ArrayList<>();
    public Boolean white, green, red, purple, brown, turquoise, orange, black, yellow, pink, blue;

    @EJB
    private ColorsList bean;

    @PostConstruct
    public void init() {
        bean.loadSolids(solidsList);
        for (int i = 0; i < solidsList.size(); i++) {
            String s = solidsList.get(i).solidsName;
            Boolean b = solidsList.get(i).isActive;
//            System.out.println("in circle "+i+" SolidName = "+s+", and isActive = "+b);

            if (s.equals("White")){
                System.out.println("I'M IN!");
                setWhite(b);
            }

            if (s.equals("Green")){
                setGreen(b);
            }
            if (s.equals("Red")){
                setRed(b);
            }
            if (s.equals("Purple")){
                setPurple(b);
            }
            if (s.equals("Brown")){
                setBrown(b);
            }
            if (s.equals("Turquoise")){
                setTurquoise(b);
            }
            if (s.equals("Orange")){
                setOrange(b);
            }
            if (s.equals("Black")){
                setBlack(b);
            }
            if (s.equals("Yellow")){
                setYellow(b);
            }
            if (s.equals("Pink")){
                setPink(b);
            }
            if (s.equals("Blue")){
                setBlue(b);
            }
        }
    }

//    public String logout(){ return "/faces/login.xhtml"; }

    public Boolean getWhite() {return white;}
    public void setWhite(Boolean white) { this.white = white; }

    public Boolean getGreen() {return green;}
    public void setGreen(Boolean green) { this.green = green; }

    public Boolean getRed() {return red;}
    public void setRed(Boolean red) { this.red = red; }

    public Boolean getPurple() {return purple;}
    public void setPurple(Boolean purple) { this.purple = purple; }

    public Boolean getBrown() {return brown;}
    public void setBrown(Boolean brown) { this.brown = brown; }

    public Boolean getTurquoise() {return turquoise;}
    public void setTurquoise(Boolean turquoise) { this.turquoise = turquoise; }

    public Boolean getOrange() {return orange;}
    public void setOrange(Boolean orange) { this.orange = orange; }

    public Boolean getBlack() {return black;}
    public void setBlack(Boolean black) { this.black = black; }

    public Boolean getYellow() {return yellow;}
    public void setYellow(Boolean yellow) { this.yellow = yellow; }

    public Boolean getPink() {return pink;}
    public void setPink(Boolean pink) { this.pink = pink; }

    public Boolean getBlue() {return blue;}
    public void setBlue(Boolean blue) { this.blue = blue; }

//    public List<SUpModel> getSolidsList() {return solidsList;}
//    public void setSolidsList(List<SUpModel> solidsList) { this.solidsList = solidsList; }

    public void addSolids (Boolean white, Boolean green, Boolean red, Boolean purple, Boolean brown, Boolean turquoise, Boolean orange, Boolean black, Boolean yellow, Boolean pink, Boolean blue) {
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm.setBoolean(1, white);
            stm.setString(2, "White");
            stm.executeUpdate();

            PreparedStatement stm1 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm1.setBoolean(1, green);
            stm1.setString(2, "Green");
            stm1.executeUpdate();

            PreparedStatement stm2 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm2.setBoolean(1, red);
            stm2.setString(2, "Red");
            stm2.executeUpdate();

            PreparedStatement stm3 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm3.setBoolean(1, purple);
            stm3.setString(2, "Purple");
            stm3.executeUpdate();

            PreparedStatement stm4 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm4.setBoolean(1, brown);
            stm4.setString(2, "Brown");
            stm4.executeUpdate();

            PreparedStatement stm5 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm5.setBoolean(1, turquoise);
            stm5.setString(2, "Turquoise");
            stm5.executeUpdate();

            PreparedStatement stm6 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm6.setBoolean(1, orange);
            stm6.setString(2, "Orange");
            stm6.executeUpdate();

            PreparedStatement stm7 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm7.setBoolean(1, black);
            stm7.setString(2, "Black");
            stm7.executeUpdate();

            PreparedStatement stm8 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm8.setBoolean(1, yellow);
            stm8.setString(2, "Yellow");
            stm8.executeUpdate();

            PreparedStatement stm9 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm9.setBoolean(1, pink);
            stm9.setString(2, "Pink");
            stm9.executeUpdate();

            PreparedStatement stm10 = connect.prepareStatement("UPDATE foot.sp_solids SET instock = ? WHERE solids_eng = ?");
            stm10.setBoolean(1, blue);
            stm10.setString(2, "Blue");
            stm10.executeUpdate();

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "Color list updated successfully.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (SQLException e) {
            System.out.println("NEW SOLIDS FROM CHECKBOX CAN'T LOAD");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "An error has occurred.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();
        }
    }
}
