package com.vnptit.vnpthis.domain;

import javax.persistence.*;

@Entity
@Table(name = "CDT_NOIDEN", schema = "PM2_FRESHER", catalog = "")
public class CdtNoiden {
    @Id
    private int noidencongtacid;
    private int chidaotuyenid;
    private int csytid;

    @Basic
    @Column(name = "NOIDENCONGTACID", nullable = false, precision = 0)
    public int getNoidencongtacid() {
        return noidencongtacid;
    }

    public void setNoidencongtacid(int noidencongtacid) {
        this.noidencongtacid = noidencongtacid;
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

        CdtNoiden cdtNoiden = (CdtNoiden) o;

        if (noidencongtacid != cdtNoiden.noidencongtacid) return false;
        if (chidaotuyenid != cdtNoiden.chidaotuyenid) return false;
        if (csytid != cdtNoiden.csytid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noidencongtacid;
        result = 31 * result + chidaotuyenid;
        result = 31 * result + csytid;
        return result;
    }
}
