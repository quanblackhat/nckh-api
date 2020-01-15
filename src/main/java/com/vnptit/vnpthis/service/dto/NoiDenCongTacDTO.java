package com.vnptit.vnpthis.service.dto;


import com.vnptit.vnpthis.domain.CdtNoidencongtac;

public class NoiDenCongTacDTO {
    private int id;
    private String manoiden;
    private String tennoiden;
    private Byte thutusapxep;
    private int csytid;

    public NoiDenCongTacDTO(CdtNoidencongtac noidencongtac) {
        id = noidencongtac.getNoidencongtacid();
        manoiden = noidencongtac.getManoiden();
        tennoiden = noidencongtac.getTennoiden();
        thutusapxep = noidencongtac.getThutusapxep();
        csytid = noidencongtac.getCsytid();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManoiden() {
        return manoiden;
    }

    public void setManoiden(String manoiden) {
        this.manoiden = manoiden;
    }

    public String getTennoiden() {
        return tennoiden;
    }

    public void setTennoiden(String tennoiden) {
        this.tennoiden = tennoiden;
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
