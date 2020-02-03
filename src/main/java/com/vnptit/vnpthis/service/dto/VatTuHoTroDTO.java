package com.vnptit.vnpthis.service.dto;

import com.vnptit.vnpthis.domain.cdt.CdtVattuhotro;

public class VatTuHoTroDTO {
    private int id;
    private String mavattu;
    private String tenvattu;
    private Byte thutusapxep;
    private int csytid;

    public VatTuHoTroDTO(CdtVattuhotro vattuhotro) {
        id = vattuhotro.getVattuhotroid();
        mavattu = vattuhotro.getMavattu();
        tenvattu = vattuhotro.getTenvattu();
        thutusapxep = vattuhotro.getThutusapxep();
        csytid = vattuhotro.getCsytid();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMavattu() {
        return mavattu;
    }

    public void setMavattu(String mavattu) {
        this.mavattu = mavattu;
    }

    public String getTenvattu() {
        return tenvattu;
    }

    public void setTenvattu(String tenvattu) {
        this.tenvattu = tenvattu;
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
