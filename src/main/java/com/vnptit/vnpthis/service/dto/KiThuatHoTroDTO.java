package com.vnptit.vnpthis.service.dto;

import com.vnptit.vnpthis.domain.cdt.CdtKythuathotro;

public class KiThuatHoTroDTO {
    private int id;
    private String makythuat;
    private String tenkythuat;
    private Byte thutusapxep;
    private int csytid;

    public KiThuatHoTroDTO(CdtKythuathotro kythuathotro) {
        id = kythuathotro.getKythuathotroid();
        makythuat = kythuathotro.getMakythuat();
        tenkythuat = kythuathotro.getTenkythuat();
        thutusapxep = kythuathotro.getThutusapxep();
        csytid = kythuathotro.getCsytid();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakythuat() {
        return makythuat;
    }

    public void setMakythuat(String makythuat) {
        this.makythuat = makythuat;
    }

    public String getTenkythuat() {
        return tenkythuat;
    }

    public void setTenkythuat(String tenkythuat) {
        this.tenkythuat = tenkythuat;
    }

    public Byte getThutusapxep() {
        return thutusapxep;
    }

    public void setThutusapxep(Byte thutusapxep) {
        this.thutusapxep = thutusapxep;
    }

    public int getCsytid() {
        return csytid;
    }

    public void setCsytid(int csytid) {
        this.csytid = csytid;
    }
}
