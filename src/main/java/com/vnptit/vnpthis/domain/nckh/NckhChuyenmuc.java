package com.vnptit.vnpthis.domain.nckh;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NCKH_CHUYENMUC", schema = "PM2_FRESHER", catalog = "")
public class NckhChuyenmuc {
    private int chuyenmucid;
    private int csytid;
    private Time ngaytao;
    private int sott;
    private String tenchuyenmuc;

    @Id
    @Column(name = "CHUYENMUCID", nullable = false, precision = 0)
    public int getChuyenmucid() {
        return chuyenmucid;
    }

    public void setChuyenmucid(int chuyenmucid) {
        this.chuyenmucid = chuyenmucid;
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
    @Column(name = "NGAYTAO", nullable = true)
    public Time getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Time ngaytao) {
        this.ngaytao = ngaytao;
    }

    @Basic
    @Column(name = "SOTT", nullable = false, precision = 0)
    public int getSott() {
        return sott;
    }

    public void setSott(int sott) {
        this.sott = sott;
    }

    @Basic
    @Column(name = "TENCHUYENMUC", nullable = true, length = 255)
    public String getTenchuyenmuc() {
        return tenchuyenmuc;
    }

    public void setTenchuyenmuc(String tenchuyenmuc) {
        this.tenchuyenmuc = tenchuyenmuc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NckhChuyenmuc that = (NckhChuyenmuc) o;

        if (chuyenmucid != that.chuyenmucid) return false;
        if (csytid != that.csytid) return false;
        if (sott != that.sott) return false;
        if (ngaytao != null ? !ngaytao.equals(that.ngaytao) : that.ngaytao != null) return false;
        if (tenchuyenmuc != null ? !tenchuyenmuc.equals(that.tenchuyenmuc) : that.tenchuyenmuc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chuyenmucid;
        result = 31 * result + csytid;
        result = 31 * result + (ngaytao != null ? ngaytao.hashCode() : 0);
        result = 31 * result + sott;
        result = 31 * result + (tenchuyenmuc != null ? tenchuyenmuc.hashCode() : 0);
        return result;
    }
}
