package ru.inpris.bean.admin;

import org.primefaces.event.RowEditEvent;
import ru.inpris.bean.IRO_SOCKET.AllSolidsUpModel;
import ru.inpris.bean.colors.SUpModel;

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
public class Admin implements Serializable {
    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;

    private List<AdminUpModel> allList = new ArrayList<>();
    private List<AllSolidsUpModel> allSolids = new ArrayList<>();
    @EJB
    private AdminList bean;

    @PostConstruct
    public void init() {
        bean.loadFoot(allList);
        bean.loadAllS(allSolids);
    }

//    public String logout(){ return "/faces/login.xhtml"; }

    public List<AllSolidsUpModel> getAllSolids() { return allSolids; }
    public void setAllSolids(List<AllSolidsUpModel> allSolids) { this.allSolids = allSolids;}

    public List<AdminUpModel> getAllList() {return allList;}
    public void setAllList(List<AdminUpModel> allList) { this.allList = allList; }

    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public void onRowEdit(RowEditEvent event) {

        bean.updateElName(((AdminUpModel) event.getObject()).getLogin(),((AdminUpModel) event.getObject()).getId(), ((AdminUpModel) event.getObject()).getFirstName(), ((AdminUpModel) event.getObject()).getLastName(), ((AdminUpModel) event.getObject()).getPatronymic(), ((AdminUpModel) event.getObject()).getDiagnosis(), ((AdminUpModel) event.getObject()).getSide(),
                ((AdminUpModel) event.getObject()).getSolids(), ((AdminUpModel) event.getObject()).getFootLength(), ((AdminUpModel) event.getObject()).getHeightToTibialTuberosity(), ((AdminUpModel) event.getObject()).getFirst(), ((AdminUpModel) event.getObject()).getSecond(), ((AdminUpModel) event.getObject()).getThird(),
                ((AdminUpModel) event.getObject()).getFourth(), ((AdminUpModel) event.getObject()).getFifth(), ((AdminUpModel) event.getObject()).getSixth(), ((AdminUpModel) event.getObject()).getSeventh(),
                ((AdminUpModel) event.getObject()).getEighth(), ((AdminUpModel) event.getObject()).getNinth(), ((AdminUpModel) event.getObject()).getAddress(), ((AdminUpModel) event.getObject()).getStatus(),
                ((AdminUpModel) event.getObject()).getMarks(), ((AdminUpModel) event.getObject()).getNotes(), convertJavaDateToSqlDate(((AdminUpModel) event.getObject()).getDconsultation()),
                convertJavaDateToSqlDate(((AdminUpModel) event.getObject()).getDisssue()), convertJavaDateToSqlDate(((AdminUpModel) event.getObject()).getInsDate()));
    }

    public void onRowCancel(RowEditEvent event) {
        System.out.println("Cancel_Edit");
    }

    public void delete (Integer id){
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("DELETE from foot.p_saved WHERE id = ?");

            stm.setInt(1, id);
            stm.executeUpdate();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Your order information has been successfully deleted.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (SQLException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "An error has occurred.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            e.printStackTrace();
        }
    }
}
