package com.vnptit.vnpthis.domain.cdt;

import javax.persistence.*;

@Entity
@Table(name = "CDT_VATTU", schema = "PM2_FRESHER", catalog = "")
public class CdtVattu {
    @Id
    private int vattuhotroid;
    private int chidaotuyenid;
    private int csytid;

    @Basic
    @Column(name = "VATTUHOTROID", nullable = false, precision = 0)
    public int getVattuhotroid() {
        return vattuhotroid;
    }

    public void setVattuhotroid(int vattuhotroid) {
        this.vattuhotroid = vattuhotroid;
    }

    @Basic
    @Column(name = "CHIDAOTUYENID", nullable = false, precision = 0)
    public int getChidaotuyenid() {
        return chidaotuyenid;
    }

    public void setChidaotuyenid(int chidaotuyenid) {
        this.chidaotuyenid = chidaotuyenid;
    }

    @Basic
    @Column(name = "CSYTID", nullable = false, precision = 0)
    public int getCsytid() {
        return csytid;
    }

    public void setCsytid(int csytid) {
        this.csytid = csytid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdtVattu cdtVattu = (CdtVattu) o;

        if (vattuhotroid != cdtVattu.vattuhotroid) return false;
        if (chidaotuyenid != cdtVattu.chidaotuyenid) return false;
        if (csytid != cdtVattu.csytid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vattuhotroid;
        result = 31 * result + chidaotuyenid;
        result = 31 * result + csytid;
        return result;
    }
}
