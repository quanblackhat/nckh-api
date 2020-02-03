package com.vnptit.vnpthis.domain.cdt;

import javax.persistence.*;

@Entity
@Table(name = "CDT_KYTHUAT", schema = "PM2_FRESHER", catalog = "")
public class CdtKythuat {
    @Id
    private int kythuathotroid;
    private int chidaotuyenid;
    private int csytid;

    @Basic
    @Column(name = "KYTHUATHOTROID", nullable = false, precision = 0)
    public int getKythuathotroid() {
        return kythuathotroid;
    }

    public void setKythuathotroid(int kythuathotroid) {
        this.kythuathotroid = kythuathotroid;
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

        CdtKythuat that = (CdtKythuat) o;

        if (kythuathotroid != that.kythuathotroid) return false;
        if (chidaotuyenid != that.chidaotuyenid) return false;
        if (csytid != that.csytid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kythuathotroid;
        result = 31 * result + chidaotuyenid;
        result = 31 * result + csytid;
        return result;
    }
}
