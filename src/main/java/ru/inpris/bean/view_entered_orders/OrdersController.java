package ru.inpris.bean.view_entered_orders;

import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

@ManagedBean (name = "orders")
@ViewScoped
public class OrdersController implements Serializable {
    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;
    private String login;
    private List<OrdersUpModel> list = new ArrayList<>();

    @EJB
    private OrdersList bean;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        if (session.getAttribute("user").toString()!=null) {
            login = session.getAttribute("user").toString();
        }
        bean.loadFoot(login, list);
    }

    public String logout(){ return "/faces/login.xhtml"; }

    public String orders(){ return "/faces/IRO_SOCKET_rus.xhtml"; }

    public List<OrdersUpModel> getList() {return list;}
    public void setList(List<OrdersUpModel> list) { this.list = list; }

    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public void onRowEdit(RowEditEvent event) {

        bean.updateElName(((OrdersUpModel) event.getObject()).getId(), ((OrdersUpModel) event.getObject()).getFirstName(), ((OrdersUpModel) event.getObject()).getLastName(), ((OrdersUpModel) event.getObject()).getPatronymic(), ((OrdersUpModel) event.getObject()).getDiagnosis(), ((OrdersUpModel) event.getObject()).getSide(),
                ((OrdersUpModel) event.getObject()).getSolids(), ((OrdersUpModel) event.getObject()).getFootLength(), ((OrdersUpModel) event.getObject()).getHeightToTibialTuberosity(), ((OrdersUpModel) event.getObject()).getFirst(), ((OrdersUpModel) event.getObject()).getSecond(), ((OrdersUpModel) event.getObject()).getThird(),
                ((OrdersUpModel) event.getObject()).getFourth(), ((OrdersUpModel) event.getObject()).getFifth(), ((OrdersUpModel) event.getObject()).getSixth(), ((OrdersUpModel) event.getObject()).getSeventh(),
                ((OrdersUpModel) event.getObject()).getEighth(), ((OrdersUpModel) event.getObject()).getNinth(), ((OrdersUpModel) event.getObject()).getAddress(), ((OrdersUpModel) event.getObject()).getStatus(),
                ((OrdersUpModel) event.getObject()).getMarks(), ((OrdersUpModel) event.getObject()).getNotes(), convertJavaDateToSqlDate(((OrdersUpModel) event.getObject()).getDconsultation()),
                convertJavaDateToSqlDate(((OrdersUpModel) event.getObject()).getDisssue()), convertJavaDateToSqlDate(((OrdersUpModel) event.getObject()).getInsDate()));
    }

    public void onRowCancel(RowEditEvent event) {
        System.out.println("Cancel_Edit");
    }

    public void delete (Integer id){
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("DELETE from foot.p_saved WHERE login = ? and id = ?");
            stm.setString(1, login);
            stm.setInt(2, id);
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
