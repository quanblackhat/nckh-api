package com.vnptit.vnpthis.service.dto;

import com.vnptit.vnpthis.domain.CdtLydocongtac;

public class LyDoCongTacDTO {
    private int id;
    private String malydo;
    private String tenlydo;
    private Byte thutusapxep;
    private int csytid;

    public LyDoCongTacDTO(CdtLydocongtac lydocongtac) {
        id = lydocongtac.getLydocongtacid();
        malydo = lydocongtac.getMalydo();
        tenlydo = lydocongtac.getTenlydo();
        thutusapxep = lydocongtac.getThutusapxep();
        csytid = lydocongtac.getCsytid();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMalydo() {
        return malydo;
    }

    public void setMalydo(String malydo) {
        this.malydo = malydo;
    }

    public String getTenlydo() {
        return tenlydo;
    }

    public void setTenlydo(String tenlydo) {
        this.tenlydo = tenlydo;
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
