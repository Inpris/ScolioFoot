package ru.inpris.bean.IRO_SOCKET;

import java.io.Serializable;

public class PUpModel implements Serializable {
    private Integer pid;
    private String pati;

    public PUpModel() {
    }

    public PUpModel(Integer pid, String pati) {
        this.pid = pid;
        this.pati = pati;
    }

    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPati() { return pati; }
    public void setPati(String pati) {
        this.pati = pati;
    }
}
