package ru.inpris.bean.IRO_SOCKET;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
//Аннототации Scope устанавливают область видимости, в которую помещается ManagedBean
@ViewScoped //  хранит контроллер пока страница не закрыта
//@RequestScoped    контроллер создаётся во время выполнения HTTP – запроса и уничтожается, когда HTTP – ответ, ассоциированный с данным запросом завершается
//@ApplicationScoped    контроллёр создаётся во время выполнения HTTP – запроса и уничтожается, когда Application на сервере приложений is undeploy
//@SessionScoped    Бин живет до тех пор, пока существует HTTP сессия

public class Foot implements Serializable /*сохраняет контроллер в файл, а не в оперативную память*/{

    @Resource(name = "jdbc/SCOLIO", mappedName = "jdbc/SCOLIO")
    private DataSource ds;

    private String patronymic, fl = "0 mm", httt = "0 mm", fir="0 mm", sec="0 mm", thi="0 mm", fou="0 mm", fif="0 mm", six="0 mm", sev="0 mm", eig="0 mm", nin="0 mm", mark, login, firstName, lastName, address, notes, diagnosis, side;
//    gender, city, stProv, postalCode, orderConfirmation, chafeOptions, cityShipping, stProvShipping, postalCodeShipping,
//    smo, company, , companyContactName, companyContactPhone, companyContactEMail, companyContactFax, modifications, allard, allardSide;
    private String lang;
//    private Boolean returnImpressions, billingAddressSame, toeWalking, openHeel, dorsalChips, nonSkidSole, houdiniStraps, carbonFiberFootplates, springSteel, adjustalift,
//    shoesPink, shoesBlue, shoesBlack, smoSocks;
    private Integer footLength, heightToTibialTuberosity, first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, solids;
//    weight, patternOptions, strapColorOptions,  shippingOptions;
    private java.util.Date dconsultation, dissue;
// = new java.util.Date(System.currentTimeMillis())  = new java.util.Date(0000-00-00)

    private List<AllSolidsUpModel> solidsList = new ArrayList<>();

    @EJB
    private FootList bean;

    @PostConstruct
    public void init() {
        lang = "rus";
        bean.loadSolidsList(solidsList, lang);
        try {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

            if (session.getAttribute("user").toString()!=null) {
                login = session.getAttribute("user").toString();
            }else {
                logout();
            }
        }catch (com.sun.faces.mgbean.ManagedBeanCreationException e){
            logout();
        }

        loadFootSaved(login);
    }

    private Integer toInt(String str) {
        Integer foo;
        String str2 = str.replaceAll("[^0-9]",""); //заменяем все не цифры на пустоту

        try {
            foo = Integer.parseInt(str2);
        } catch (NumberFormatException e) {
            foo = -1;
        }
        return foo;
    }

    public java.util.Date getDissue() {return dissue;}
    public void setDissue(java.util.Date dissue) { this.dissue = dissue; }

    public java.util.Date getDconsultation() {return dconsultation;}
    public void setDconsultation(java.util.Date dconsultation) { this.dconsultation = dconsultation; }

    public String getPatronymic() {return patronymic;}
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getDiagnosis() {return diagnosis;}
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getAddress() {return address;}
    public void setAddress(String address) { this.address = address; }

    public String getNotes() {return notes;}
    public void setNotes(String notes) { this.notes = notes; }

    public String getMark() {return mark;}
    public void setMark(String mark) { this.mark = mark; }

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) { this.lastName = lastName; }

    public List<AllSolidsUpModel> getSolidsList() { return solidsList; }
    public void setSolidsList(List<AllSolidsUpModel> solidsList) {
        this.solidsList = solidsList;
    }

//    public String getGender() {return gender;}
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//    public Boolean getBillingAddressSame() {return billingAddressSame;}
//    public void setBillingAddressSame(Boolean billingAddressSame) {
//        this.billingAddressSame = billingAddressSame;
//    }
//    public String getCity() {return city;}
//    public void setCity(String city) {
//        this.city = city;
//    }
//    public String getStProv() {return stProv;}
//    public void setStProv(String stProv) {
//        this.stProv = stProv;
//    }
//    public String getPostalCode() {return postalCode;}
//    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
//    public String getCityShipping() {return cityShipping;}
//    public void setCityShipping(String cityShipping) {
//        this.cityShipping = cityShipping;
//    }
//    public String getStProvShipping() {return stProvShipping;}
//    public void setStProvShipping(String stProvShipping) {
//        this.stProvShipping = stProvShipping;
//    }
//    public String getPostalCodeShipping() {return postalCodeShipping;}
//    public void setPostalCodeShipping(String postalCodeShipping) { this.postalCodeShipping = postalCodeShipping; }
//    public Integer getWeight() {return weight;}
//    public void setWeight(Integer weight) { this.weight = weight; }
//    public String getCompany() {return company;}
//    public void setCompany(String company) { this.company = company; }

    public String getFl() {return fl;}
    public void setFl(String fl) { this.fl = fl; }

//    public String getCompanyContactName() {return companyContactName;}
//    public void setCompanyContactName(String companyContactName) { this.companyContactName = companyContactName; }
//    public String getCompanyContactPhone() {return companyContactPhone;}
//    public void setCompanyContactPhone(String companyContactPhone) {
//        this.companyContactPhone = companyContactPhone;
//    }
//    public String getCompanyContactEMail() {return companyContactEMail;}
//    public void setCompanyContactEMail(String companyContactEMail) {
//        this.companyContactEMail = companyContactEMail;
//    }
//    public String getCompanyContactFax() {return companyContactFax;}
//    public void setCompanyContactFax(String companyContactFax) {
//        this.companyContactFax = companyContactFax;
//    }
//    public String getOrderConfirmation() {return orderConfirmation;}
//    public void setOrderConfirmation(String orderConfirmation) {
//        this.orderConfirmation = orderConfirmation;
//    }

    public String getSide() {return side;}
    public void setSide(String side) {
        this.side = side;
    }

//    public Integer getPatternOptions() {return patternOptions;}
//    public void setPatternOptions(Integer patternOptions) {
//        this.patternOptions = patternOptions;
//    }
//    public Integer getStrapColorOptions() {return strapColorOptions;}
//    public void setStrapColorOptions(Integer strapColorOptions) {
//        this.strapColorOptions = strapColorOptions;
//    }
//    public String getChafeOptions() {return chafeOptions;}
//    public void setChafeOptions(String chafeOptions) { this.chafeOptions = chafeOptions; }
//
//    public String getSmo() {return smo;}
//    public void setSmo(String smo) {
//        this.smo = smo;
//    }
//    public Boolean getToeWalking() {return toeWalking;}
//    public void setToeWalking(Boolean toeWalking) {
//        this.toeWalking = toeWalking;
//    }
//    public Boolean getOpenHeel() {return openHeel;}
//    public void setOpenHeel(Boolean openHeel) {
//        this.openHeel = openHeel;
//    }
//    public Boolean getDorsalChips() {return dorsalChips;}
//    public void setDorsalChips(Boolean dorsalChips) {
//        this.dorsalChips = dorsalChips;
//    }
//    public Boolean getNonSkidSole() {return nonSkidSole;}
//    public void setNonSkidSole(Boolean nonSkidSole) {
//        this.nonSkidSole = nonSkidSole;
//    }
//    public Boolean getHoudiniStraps() {return houdiniStraps;}
//    public void setHoudiniStraps(Boolean houdiniStraps) {
//        this.houdiniStraps = houdiniStraps;
//    }
//    public Boolean getCarbonFiberFootplates() {return carbonFiberFootplates;}
//    public void setCarbonFiberFootplates(Boolean carbonFiberFootplates) { this.carbonFiberFootplates = carbonFiberFootplates; }
//
//    public Boolean getSpringSteel() {return springSteel;}
//    public void setSpringSteel(Boolean springSteel) {
//        this.springSteel = springSteel;
//    }
//    public Boolean getAdjustalift() {return adjustalift;}
//    public void setAdjustalift(Boolean adjustalift) {
//        this.adjustalift = adjustalift;
//    }
//    public Boolean getShoesPink() {return shoesPink;}
//    public void setShoesPink(Boolean shoesPink) {
//        this.shoesPink = shoesPink;
//    }
//    public Boolean getShoesBlue() {return shoesBlue;}
//    public void setShoesBlue(Boolean shoesBlue) {
//        this.shoesBlue = shoesBlue;
//    }
//    public Boolean getShoesBlack() {return shoesBlack;}
//    public void setShoesBlack(Boolean shoesBlack) {
//        this.shoesBlack = shoesBlack;
//    }
//    public Boolean getSmoSocks() {return smoSocks;}
//    public void setSmoSocks(Boolean smoSocks) { this.smoSocks = smoSocks; }
//    public String getModifications() {return modifications;}
//    public void setModifications(String modifications) { this.modifications = modifications; }
//    public String getAllard() {return allard;}
//    public void setAllard(String allard) { this.allard = allard; }
//    public String getAllardSide() {return allardSide;}
//    public void setAllardSide(String allardSide) { this.allardSide = allardSide; }

    public Integer getSolids() {return solids;}
    public void setSolids(Integer solids) { this.solids = solids; }

//    public Integer getShippingOptions() {return shippingOptions;}
//    public void setShippingOptions(Integer shippingOptions) { this.shippingOptions = shippingOptions; }
//    public Boolean getReturnImpressions() {return returnImpressions;}
//    public void setReturnImpressions(Boolean returnImpressions) { this.returnImpressions = returnImpressions; }

    public String getHttt() {return httt;}
    public void setHttt(String httt) {this.httt = httt; }

    public String getFir() {return fir;}
    public void setFir(String fir) { this.fir = fir; }

    public String getSec() {return sec;}
    public void setSec(String sec) { this.sec = sec; }

    public String getThi() {return thi;}
    public void setThi(String thi) { this.thi = thi; }

    public String getFou() {return fou;}
    public void setFou(String fou) { this.fou = fou; }

    public String getFif() {return fif;}
    public void setFif(String fif) { this.fif = fif; }

    public String getSix() {return six;}
    public void setSix(String six) { this.six = six; }

    public String getSev() {return sev;}
    public void setSev(String sev) { this.sev = sev; }

    public String getEig() {return eig;}
    public void setEig(String eig) { this.eig = eig; }

    public String getNin() {return nin;}
    public void setNin(String nin) { this.nin = nin; }

//    public Date getNeedByDate() {return needByDate;}
//    public void setNeedByDate(Date needByDate) { this.needByDate = needByDate; }
//
//    public Date getDob() { return dob;}
//    public void setDob (Date dob) { this.dob = dob; }

    private java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public void addAll(int j) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        login = session.getAttribute("user").toString();

        first = toInt(fir);
        second = toInt(sec);
        third = toInt(thi);
        fourth = toInt(fou);
        fifth = toInt(fif);
        sixth = toInt(six);
        seventh = toInt(sev);
        eighth = toInt(eig);
        ninth = toInt(nin);
        heightToTibialTuberosity = toInt(httt);
        footLength = toInt(fl);

        if ((j==2)&(firstName!=null)&(lastName!=null)&(side!=null)&(mark!=null)&(notes!=null)&(patronymic!=null)&
//                (gender!=null)&(city!=null)&(stProv!=null)&(postalCode!=null)&(orderConfirmation!=null)&
//                (chafeOptions!=null)&(cityShipping!=null)&(stProvShipping!=null)&(postalCodeShipping!=null)&(smo!=null)&
//                (company!=null)&(companyContactName!=null)&(companyContactPhone!=null)&(companyContactEMail!=null)&
//                (companyContactFax!=null)&(modifications!=null)&(allard!=null)&(allardSide!=null)&(weight!=null)&
                (footLength!=null)&(first!=null)&(second!=null)&(third!=null)&(fourth!=null)&
                (fifth!=null)&(sixth!=null)&(seventh!=null)&(eighth!=null)&(ninth!=null)&(solids!=null)&
//                (patternOptions!=null)&(strapColorOptions!=null)&(shippingOptions!=null)&
                (heightToTibialTuberosity!=null)&(dissue!=null)&(dconsultation!=null)&(diagnosis!=null)&(address!=null)){

            String status = "finished";

            bean.loadNewFoot(heightToTibialTuberosity, status, login, firstName, lastName, side, convertJavaDateToSqlDate(dissue), solids,
//                    gender, cityShipping,  stProvShipping , postalCodeShipping, city, stProv, postalCode, orderConfirmation, patternOptions, strapColorOptions,
//                    smo, company, companyContactName, companyContactPhone, companyContactEMail, weight, companyContactFax, modifications, allard, allardSide, shippingOptions, returnImpressions, chafeOptions,
                    footLength, first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, convertJavaDateToSqlDate(dconsultation), mark, address, diagnosis, notes, patronymic);
//                    toeWalking, openHeel, dorsalChips, nonSkidSole, houdiniStraps, carbonFiberFootplates, springSteel, adjustalift, shoesPink, shoesBlue, shoesBlack, smoSocks);
            setFirstName(null);
            setLastName(null);
            setSide(null);
//            setGender(null);
//            setBillingAddressSame(false);
//            setCity(null);
//            setStProv(null);
//            setPostalCode(null);
//            setWeight(null);
//            setCompany(null);
//            setCompanyContactEMail(null);
//            setCompanyContactFax(null);
//            setCompanyContactName(null);
//            setCompanyContactPhone(null);
            setFl("0 mm");
//            setOrderConfirmation(null);
//            setPatternOptions(null);
//            setStrapColorOptions(null);
//            setChafeOptions(null);
//            setSmo(null);
//            setToeWalking(false);
//            setOpenHeel(false);
//            setDorsalChips(false);
//            setNonSkidSole(false);
//            setHoudiniStraps(false);
//            setCarbonFiberFootplates(false);
//            setSpringSteel(false);
//            setAdjustalift(false);
//            setShoesPink(false);
//            setShoesBlue(false);
//            setShoesBlack(false);
//            setSmoSocks(false);
//            setModifications(null);
//            setAllard(null);
//            setAllardSide(null);
            setSolids(null);
//            setShippingOptions(null);
//            setReturnImpressions(false);
            setHttt("0 mm");
            setFir("0 mm");
            setSec("0 mm");
            setThi("0 mm");
            setFou("0 mm");
            setFif("0 mm");
            setSix("0 mm");
            setSev("0 mm");
            setEig("0 mm");
            setNin("0 mm");
            setDissue(null);
            setDconsultation (null);
            setMark(null);
            setNotes(null);
            setAddress(null);
            setDiagnosis(null);
            setPatronymic(null);
//            setCityShipping(null);
//            setStProvShipping(null);
//            setPostalCodeShipping(null);

            context.addMessage(null, new FacesMessage("Your details have been successfully entered."));
        }
        else if (j==1){
            String status = "edited";
            if (dissue==null){
               dissue  = new java.util.Date(0000-00-00);
            }
            bean.loadNewFoot(heightToTibialTuberosity, status, login, firstName, lastName, side, convertJavaDateToSqlDate(dissue), solids,
//                    gender, cityShipping,  stProvShipping , postalCodeShipping, city, stProv, postalCode, orderConfirmation, patternOptions, strapColorOptions,
//                    smo, company, companyContactName, companyContactPhone, companyContactEMail, companyContactFax, modifications, allard, allardSide, shippingOptions, returnImpressions, chafeOptions,
                    footLength, first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, convertJavaDateToSqlDate(dconsultation), mark, address, diagnosis, notes, patronymic);
//                    toeWalking, openHeel, dorsalChips, nonSkidSole, houdiniStraps, carbonFiberFootplates, springSteel, adjustalift, shoesPink, shoesBlue, shoesBlack, smoSocks);

            context.addMessage(null, new FacesMessage("Your details have been successfully saved."));
        }
        else {context.addMessage(null, new FacesMessage("Not all fields have been entered."));}
    }

    public String logout (){
        FacesContext  context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        HttpSession se = request.getSession();
        se.setAttribute("user", "null");
        se.setAttribute("isLoggedIn", null);

        solidsList = new ArrayList<>();

        return "/faces/login.xhtml";
    }

    public String orders (){
        return "/faces/view_entered_orders.xhtml";
    }

//    public String menuName () {
//        re
//    }

    public String menu (){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

            HttpSession se = request.getSession();
            Object role = se.getAttribute("role");
            if (role.equals(1)) {
                return "/faces/view_entered_orders.xhtml";
            } else if (role.equals(2)) {
                return "/faces/allOrders.xhtml";
            } else return "/faces/login.xhtml";
    }

    public String RUS (){
        lang = "rus";
        solidsList = new ArrayList<>();
        bean.loadSolidsList(solidsList, lang);
        return "/faces/IRO_SOCKET_eng.xhtml";
    }

    public String ENG (){
        lang = "eng";
        solidsList = new ArrayList<>();
        bean.loadSolidsList(solidsList, lang);
        return "/faces/IRO_SOCKET_rus.xhtml";
    }

    private void loadFootSaved(String login) {
        try (Connection connect = ds.getConnection()) {
            PreparedStatement stm = connect.prepareStatement("SELECT first_name, solids, last_name, side, dconsultation, address, " +
                    "diagnosis, dissue, foot_length, first, second, third, " +
                    "fourth, fifth, sixth, seventh, eighth, ninth, status, height_to_tibial_tuberosity, marks, notes, patronymic " +
                    "FROM foot.p_saved WHERE status = 'edited' and login = '" + login +"'");
            ResultSet res = stm.executeQuery();
            res.next();

            if (res.getString("first_name") != null) {
                setFirstName(res.getString("first_name"));
            }
            if (res.getString("last_name") != null) {
                setLastName(res.getString("last_name"));
            }
            if (res.getString("side") != null) {
                setSide(res.getString("side"));
            }
            if (res.getString("marks") != null) {
                setMark(res.getString("marks"));
            }
            if (res.getString("address") != null) {
                setAddress(res.getString("address"));
            }
            if (res.getString("diagnosis") != null) {
                setDiagnosis(res.getString("diagnosis"));
            }
            if (res.getString("notes") != null) {
                setNotes(res.getString("notes"));
            }
            if (res.getString("patronymic") != null) {
                setPatronymic(res.getString("patronymic"));
            }
            if (res.getString("solids")!=null) {
                if (res.getInt("solids")!=-1){
                    setSolids(res.getInt("solids"));
                }else {setSolids(null);}
            }
            if (res.getDate("dconsultation")!=null) {
                setDconsultation(res.getDate("dconsultation"));
            }
            if (res.getDate("dissue")!=null) {
                setDissue(res.getDate("dissue"));
            }
            if (res.getString("foot_length")!=null) {
                    setFl((res.getString("foot_length"))+" mm");
                    footLength = res.getInt("foot_length");
            }
            if (res.getString("height_to_tibial_tuberosity")!=null) {
                    setHttt(res.getString("height_to_tibial_tuberosity")+" mm");
                    heightToTibialTuberosity = res.getInt("height_to_tibial_tuberosity");
            }
            if (res.getString("first")!=null) {
                    setFir(res.getString("first")+" mm");
                    first = res.getInt("first");
            }
            if (res.getString("second")!=null) {
                    setSec(res.getString("second")+" mm");
                    second = res.getInt("second");
            }
            if (res.getString("third")!=null) {
                    setThi(res.getString("third")+" mm");
                    third = res.getInt("third");
            }
            if (res.getString("fourth")!=null) {
                    setFou(res.getString("fourth")+" mm");
                    fourth = res.getInt("fourth");
            }
            if (res.getString("fifth")!=null) {
                    setFif(res.getString("fifth")+" mm");
                    fifth = res.getInt("fifth");
            }
            if (res.getString("sixth")!=null) {
                    setSix(res.getString("sixth")+" mm");
                    sixth = res.getInt("sixth");
            }
            if (res.getString("seventh")!=null) {
                    setSev(res.getString("seventh")+" mm");
                    seventh = res.getInt("seventh");
            }
            if (res.getString("eighth")!=null) {
                    setEig(res.getString("eighth")+" mm");
                    eighth = res.getInt("eighth");
            }
            if (res.getString("ninth")!=null) {
                    setNin(res.getString("ninth")+" mm");
                    ninth = res.getInt("ninth");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void handleToggle(ToggleEvent event) {
//        if (event.getVisibility().toString().equals("HIDDEN")){
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Toggled", "Shipping address same as billing.");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            str = "Shipping address same as billing";
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Toggled", "Enter Shipping Address. Shipping address not the same as billing.");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            str = "Shipping address not the same as billing";
//        }
//    }
}
