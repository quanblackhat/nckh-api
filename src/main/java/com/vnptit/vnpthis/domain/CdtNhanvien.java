package com.vnptit.vnpthis.domain;

import javax.persistence.*;

@Entity
@Table(name = "CDT_NHANVIEN", schema = "PM2_FRESHER", catalog = "")
public class CdtNhanvien {
    @Id
    private int nhanvienid;
    private int officerid;
    private int csytid;
    private int chidaotuyenid;

    @Basic
    @Column(name = "NHANVIENID", nullable = false, precision = 0)
    public int getNhanvienid() {
        return nhanvienid;
    }

    public void setNhanvienid(int nhanvienid) {
        this.nhanvienid = nhanvienid;
    }

    @Basic
    @Column(name = "OFFICERID", nullable = false, precision = 0)
    public int getOfficerid() {
        return officerid;
    }

    public void setOfficerid(int officerid) {
        this.officerid = officerid;
    }

    @Basic
    @Column(name = "CSYTID", nullable = false, precision = 0)
    public int getCsytid() {
        return csytid;
    }

    public void setCsytid(int csytid) {
        this.csytid = csytid;
    }

    @Basic
    @Column(name = "CHIDAOTUYENID", nullable = false, precision = 0)
    public int getChidaotuyenid() {
        return chidaotuyenid;
    }

    public void setChidaotuyenid(int chidaotuyenid) {
        this.chidaotuyenid = chidaotuyenid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdtNhanvien that = (CdtNhanvien) o;

        if (nhanvienid != that.nhanvienid) return false;
        if (officerid != that.officerid) return false;
        if (csytid != that.csytid) return false;
        if (chidaotuyenid != that.chidaotuyenid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nhanvienid;
        result = 31 * result + officerid;
        result = 31 * result + csytid;
        result = 31 * result + chidaotuyenid;
        return result;
    }
}
