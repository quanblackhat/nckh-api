package com.vnptit.vnpthis.domain;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NCKH_DANHGIA", schema = "PM2_FRESHER", catalog = "")
public class NckhDanhgia {
    @Id
    private int danhgiaid;
    private int loaidanhgiaid;
    private Long diemanhgia;
    private int detaiid;
    private String ghichu;
    private Time ngayCn;
    private int nguoiCn;

    @Basic
    @Column(name = "DANHGIAID", nullable = false, precision = 0)
    public int getDanhgiaid() {
        return danhgiaid;
    }

    public void setDanhgiaid(int danhgiaid) {
        this.danhgiaid = danhgiaid;
    }

    @Basic
    @Column(name = "LOAIDANHGIAID", nullable = false, precision = 0)
    public int getLoaidanhgiaid() {
        return loaidanhgiaid;
    }

    public void setLoaidanhgiaid(int loaidanhgiaid) {
        this.loaidanhgiaid = loaidanhgiaid;
    }

    @Basic
    @Column(name = "DIEMANHGIA", nullable = true, precision = 2)
    public Long getDiemanhgia() {
        return diemanhgia;
    }

    public void setDiemanhgia(Long diemanhgia) {
        this.diemanhgia = diemanhgia;
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
    @Column(name = "GHICHU", nullable = true, length = 255)
    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Basic
    @Column(name = "NGAY_CN", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NckhDanhgia that = (NckhDanhgia) o;

        if (danhgiaid != that.danhgiaid) return false;
        if (loaidanhgiaid != that.loaidanhgiaid) return false;
        if (detaiid != that.detaiid) return false;
        if (nguoiCn != that.nguoiCn) return false;
        if (diemanhgia != null ? !diemanhgia.equals(that.diemanhgia) : that.diemanhgia != null) return false;
        if (ghichu != null ? !ghichu.equals(that.ghichu) : that.ghichu != null) return false;
        if (ngayCn != null ? !ngayCn.equals(that.ngayCn) : that.ngayCn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = danhgiaid;
        result = 31 * result + loaidanhgiaid;
        result = 31 * result + (diemanhgia != null ? diemanhgia.hashCode() : 0);
        result = 31 * result + detaiid;
        result = 31 * result + (ghichu != null ? ghichu.hashCode() : 0);
        result = 31 * result + (ngayCn != null ? ngayCn.hashCode() : 0);
        result = 31 * result + nguoiCn;
        return result;
    }
}
