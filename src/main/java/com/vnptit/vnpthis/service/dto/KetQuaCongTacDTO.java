package com.vnptit.vnpthis.service.dto;

import com.vnptit.vnpthis.domain.CdtKetquacongtac;

public class KetQuaCongTacDTO {
    private int id;
    private String maketqua;
    private String tenketqua;
    private Byte thutusapxep;
    private int csytid;

    public KetQuaCongTacDTO(CdtKetquacongtac ketquacongtac) {
        id          = ketquacongtac.getKetquacongtacid();
        maketqua    = ketquacongtac.getMaketqua();
        tenketqua   = ketquacongtac.getTenketqua();
        thutusapxep = ketquacongtac.getThutusapxep();
        csytid      = ketquacongtac.getCsytid();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaketqua() {
        return maketqua;
    }

    public void setMaketqua(String maketqua) {
        this.maketqua = maketqua;
    }

    public String getTenketqua() {
        return tenketqua;
    }

    public void setTenketqua(String tenketqua) {
        this.tenketqua = tenketqua;
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
