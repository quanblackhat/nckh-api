package com.vnptit.vnpthis.domain;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NCKH_NHANSU", schema = "PM2_FRESHER", catalog = "")
public class NckhNhansu {
    private int nhansuid;
    private boolean chunhiemdetai;
    private int csytid;
    private int detaiid;
    private Time ngayCn;
    private int nguoiCn;
    private int officerid;

    @Id
    @Column(name = "NHANSUID", nullable = false, precision = 0)
    public int getNhansuid() {
        return nhansuid;
    }

    public void setNhansuid(int nhansuid) {
        this.nhansuid = nhansuid;
    }

    @Basic
    @Column(name = "CHUNHIEMDETAI", nullable = false, precision = 0)
    public boolean isChunhiemdetai() {
        return chunhiemdetai;
    }

    public void setChunhiemdetai(boolean chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
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
    @Column(name = "DETAIID", nullable = false, precision = 0)
    public int getDetaiid() {
        return detaiid;
    }

    public void setDetaiid(int detaiid) {
        this.detaiid = detaiid;
    }

    @Basic
    @Column(name = "NGAY_CN", nullable = true)
    public Time getNgayCn() {
        return ngayCn;
    }

    public void setNgayCn(Time ngayCn) {
        this.ngayCn = ngayCn;
    }

    @Basic
    @Column(name = "NGUOI_CN", nullable = false, precision = 0)
    public int getNguoiCn() {
        return nguoiCn;
    }

    public void setNguoiCn(int nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    @Basic
    @Column(name = "OFFICERID", nullable = false, precision = 0)
    public int getOfficerid() {
        return officerid;
    }

    public void setOfficerid(int officerid) {
        this.officerid = officerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NckhNhansu that = (NckhNhansu) o;

        if (nhansuid != that.nhansuid) return false;
        if (chunhiemdetai != that.chunhiemdetai) return false;
        if (csytid != that.csytid) return false;
        if (detaiid != that.detaiid) return false;
        if (nguoiCn != that.nguoiCn) return false;
        if (officerid != that.officerid) return false;
        if (ngayCn != null ? !ngayCn.equals(that.ngayCn) : that.ngayCn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nhansuid;
        result = 31 * result + (chunhiemdetai ? 1 : 0);
        result = 31 * result + csytid;
        result = 31 * result + detaiid;
        result = 31 * result + (ngayCn != null ? ngayCn.hashCode() : 0);
        result = 31 * result + nguoiCn;
        result = 31 * result + officerid;
        return result;
    }
}
