package com.vnptit.vnpthis.domain.nckh;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NCKH_DANHMUC", schema = "PM2_FRESHER", catalog = "")
public class NckhDanhmuc {
    private int loaiid;
    private Integer csytid;
    private int danhmucid;
    private String ma;
    private Time ngaytao;
    private Boolean sudung;
    private String ten;
    private Byte thutu;

    @Id
    @Column(name = "LOAIID", nullable = false, precision = 0)
    public int getLoaiid() {
        return loaiid;
    }

    public void setLoaiid(int loaiid) {
        this.loaiid = loaiid;
    }

    @Basic
    @Column(name = "CSYTID", nullable = true, precision = 0)
    public Integer getCsytid() {
        return csytid;
    }

    public void setCsytid(Integer csytid) {
        this.csytid = csytid;
    }

    @Basic
    @Column(name = "DANHMUCID", nullable = false, precision = 0)
    public int getDanhmucid() {
        return danhmucid;
    }

    public void setDanhmucid(int danhmucid) {
        this.danhmucid = danhmucid;
    }

    @Basic
    @Column(name = "MA", nullable = true, length = 255)
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    @Basic
    @Column(name = "NGAYTAO", nullable = true)
    public Time getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Time ngaytao) {
        this.ngaytao = ngaytao;
    }

    @Basic
    @Column(name = "SUDUNG", nullable = true, precision = 0)
    public Boolean getSudung() {
        return sudung;
    }

    public void setSudung(Boolean sudung) {
        this.sudung = sudung;
    }

    @Basic
    @Column(name = "TEN", nullable = true, length = 255)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "THUTU", nullable = true, precision = 0)
    public Byte getThutu() {
        return thutu;
    }

    public void setThutu(Byte thutu) {
        this.thutu = thutu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NckhDanhmuc that = (NckhDanhmuc) o;

        if (loaiid != that.loaiid) return false;
        if (danhmucid != that.danhmucid) return false;
        if (csytid != null ? !csytid.equals(that.csytid) : that.csytid != null) return false;
        if (ma != null ? !ma.equals(that.ma) : that.ma != null) return false;
        if (ngaytao != null ? !ngaytao.equals(that.ngaytao) : that.ngaytao != null) return false;
        if (sudung != null ? !sudung.equals(that.sudung) : that.sudung != null) return false;
        if (ten != null ? !ten.equals(that.ten) : that.ten != null) return false;
        if (thutu != null ? !thutu.equals(that.thutu) : that.thutu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loaiid;
        result = 31 * result + (csytid != null ? csytid.hashCode() : 0);
        result = 31 * result + danhmucid;
        result = 31 * result + (ma != null ? ma.hashCode() : 0);
        result = 31 * result + (ngaytao != null ? ngaytao.hashCode() : 0);
        result = 31 * result + (sudung != null ? sudung.hashCode() : 0);
        result = 31 * result + (ten != null ? ten.hashCode() : 0);
        result = 31 * result + (thutu != null ? thutu.hashCode() : 0);
        return result;
    }
}
