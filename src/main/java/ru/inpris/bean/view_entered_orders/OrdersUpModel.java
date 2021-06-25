package ru.inpris.bean.view_entered_orders;

import java.io.Serializable;
import java.util.Date;

public class OrdersUpModel implements Serializable {
    private Integer id, heightToTibialTuberosity, solids, footLength, first, second, third, fourth, fifth,
            sixth, seventh, eighth, ninth;
//    Boolean returnImpressions, openHeel, dorsalChips, nonSkidSole, houdiniStraps, carbonFiberFootplates,
//    springSteel, adjustalift, shoesPink, shoesBlue, shoesBlack, smoSocks, toeWalking;
    private Date dconsultation, disssue, insDate;
    private String address, diagnosis, status, firstName, lastName, side, marks, notes, patronymic;
//            smo, modifications, allard, allardSide;

    public OrdersUpModel() {
    }

    public OrdersUpModel(Integer id, String firstName, String lastName, String patronymic, String diagnosis, String side,
                         Integer solids, Integer footLength, Integer heightToTibialTuberosity, Integer first, Integer second, Integer third, Integer fourth,
                         Integer fifth, Integer sixth, Integer seventh, Integer eighth, Integer ninth, String address,
                         String status, String marks, String notes, Date dconsultation, Date disssue, Date insDate) {
        this.id = id;
        this.heightToTibialTuberosity = heightToTibialTuberosity;
        this.solids = solids;
        this.footLength = footLength;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
        this.eighth = eighth;
        this.ninth = ninth;
        this.insDate = insDate;
        this.dconsultation = dconsultation;
        this.disssue = disssue;
        this.patronymic = patronymic;
        this.marks = marks;
        this.firstName = firstName;
        this.lastName = lastName;
        this.side = side;
        this.notes = notes;
        this.diagnosis = diagnosis;
        this.address = address;
        this.status = status;
    }

    public Integer getHeightToTibialTuberosity() { return heightToTibialTuberosity; }
    public void setHeightToTibialTuberosity(Integer heightToTibialTuberosity) { this.heightToTibialTuberosity = heightToTibialTuberosity; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getSolids() { return solids; }
    public void setSolids(Integer solids) { this.solids = solids; }

    public Integer getFootLength() { return footLength; }
    public void setFootLength(Integer footLength) { this.footLength = footLength; }
    public Integer getFirst() { return first; }
    public void setFirst(Integer first) { this.first = first; }
    public Integer getSecond() { return second; }
    public void setSecond(Integer second) { this.second = second; }
    public Integer getThird() { return third; }
    public void setThird(Integer third) { this.third = third; }
    public Integer getFourth() { return fourth; }
    public void setFourth(Integer fourth) { this.fourth = fourth; }
    public Integer getFifth() { return fifth; }
    public void setFifth(Integer fifth) { this.fifth = fifth; }
    public Integer getSixth() { return sixth; }
    public void setSixth(Integer sixth) { this.sixth = sixth; }
    public Integer getSeventh() { return seventh; }
    public void setSeventh(Integer seventh) { this.seventh = seventh; }
    public Integer getEighth() { return eighth; }
    public void setEighth(Integer eighth) { this.eighth = eighth; }
    public Integer getNinth() { return ninth; }
    public void setNinth(Integer ninth) { this.ninth = ninth; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getMarks() { return marks; }
    public void setMarks(String marks) { this.marks =marks; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSide() { return side; }
    public void setSide(String side) { this.side = side; }

    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public Date getDconsultation() { return dconsultation; }
    public void setDconsultation(Date dconsultation) { this.dconsultation = dconsultation; }
    public Date getDisssue() { return disssue; }
    public void setDisssue(Date disssue) { this.disssue = disssue; }
    public Date getInsDate() { return insDate; }
    public void setInsDate(Date insDate) { this.insDate = insDate; }

//    public Boolean getDorsalChips() { return dorsalChips; }
//    public void setDorsalChips(Boolean dorsalChips) { this.dorsalChips = dorsalChips; }
//    public Boolean getNonSkidSole() { return nonSkidSole; }
//    public void setNonSkidSole(Boolean nonSkidSole) { this.nonSkidSole = nonSkidSole; }
//    public Boolean getHoudiniStraps() { return houdiniStraps; }
//    public void setHoudiniStraps(Boolean houdiniStraps) { this.houdiniStraps = houdiniStraps; }
//    public Boolean getCarbonFiberFootplates() { return carbonFiberFootplates; }
//    public void setCarbonFiberFootplates(Boolean carbonFiberFootplates) { this.carbonFiberFootplates = carbonFiberFootplates; }
//    public Boolean getSpringSteel() { return springSteel; }
//    public void setSpringSteel(Boolean springSteel) { this.springSteel = springSteel; }
//    public Boolean getAdjustalift() { return adjustalift; }
//    public void setAdjustalift(Boolean adjustalift) { this.adjustalift = adjustalift; }
//    public Boolean getReturnImpressions() { return returnImpressions; }
//    public void setReturnImpressions(Boolean returnImpressions) { this.returnImpressions = returnImpressions; }
//    public Boolean getOpenHeel() { return openHeel; }
//    public void setOpenHeel(Boolean openHeel) { this.openHeel = openHeel; }
//    public Boolean getShoesPink() { return shoesPink; }
//    public void setShoesPink(Boolean shoesPink) { this.shoesPink = shoesPink; }
//    public Boolean getShoesBlue() { return shoesBlue; }
//    public void setShoesBlue(Boolean shoesBlue) { this.shoesBlue = shoesBlue; }
//    public Boolean getShoesBlack() { return shoesBlack; }
//    public void setShoesBlack(Boolean shoesBlack) { this.shoesBlack = shoesBlack; }
//    public Boolean getSmoSocks() { return smoSocks; }
//    public void setSmoSocks(Boolean smoSocks) { this.smoSocks = smoSocks; }
//    public Boolean getToeWalking() { return toeWalking; }
//    public void setToeWalking(Boolean toeWalking) { this.toeWalking = toeWalking; }
}
