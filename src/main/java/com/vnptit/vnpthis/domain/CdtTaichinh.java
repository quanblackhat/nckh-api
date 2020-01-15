package com.vnptit.vnpthis.domain;

import javax.persistence.*;

@Entity
@Table(name = "CDT_TAICHINH", schema = "PM2_FRESHER", catalog = "")
public class CdtTaichinh {
    @Id
    private int taichinhid;
    private Byte loai;
    private Long sotien;
    private int csytid;
    private int chidaotuyenid;

    @Basic
    @Column(name = "TAICHINHID", nullable = false, precision = 0)
    public int getTaichinhid() {
        return taichinhid;
    }

    public void setTaichinhid(int taichinhid) {
        this.taichinhid = taichinhid;
    }

    @Basic
    @Column(name = "LOAI", nullable = true, precision = 0)
    public Byte getLoai() {
        return loai;
    }

    public void setLoai(Byte loai) {
        this.loai = loai;
    }

    @Basic
    @Column(name = "SOTIEN", nullable = true, precision = 0)
    public Long getSotien() {
        return sotien;
    }

    public void setSotien(Long sotien) {
        this.sotien = sotien;
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

        CdtTaichinh that = (CdtTaichinh) o;

        if (taichinhid != that.taichinhid) return false;
        if (csytid != that.csytid) return false;
        if (chidaotuyenid != that.chidaotuyenid) return false;
        if (loai != null ? !loai.equals(that.loai) : that.loai != null) return false;
        if (sotien != null ? !sotien.equals(that.sotien) : that.sotien != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taichinhid;
        result = 31 * result + (loai != null ? loai.hashCode() : 0);
        result = 31 * result + (sotien != null ? sotien.hashCode() : 0);
        result = 31 * result + csytid;
        result = 31 * result + chidaotuyenid;
        return result;
    }
}
