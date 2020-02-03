package com.vnptit.vnpthis.domain.nckh;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NCKH_DUTOAN", schema = "PM2_FRESHER", catalog = "")
public class NckhDutoan {
    @Id
    private int dutoanid;
    private short loaidutoanid;
    private Long tienDukien;
    private Long tienHoanthanh;
    private int detaiid;
    private String ghichu;
    private int nguoiCn;
    private Time ngayCn;

    @Basic
    @Column(name = "DUTOANID", nullable = false, precision = 0)
    public int getDutoanid() {
        return dutoanid;
    }

    public void setDutoanid(int dutoanid) {
        this.dutoanid = dutoanid;
    }

    @Basic
    @Column(name = "LOAIDUTOANID", nullable = false, precision = 0)
    public short getLoaidutoanid() {
        return loaidutoanid;
    }

    public void setLoaidutoanid(short loaidutoanid) {
        this.loaidutoanid = loaidutoanid;
    }

    @Basic
    @Column(name = "TIEN_DUKIEN", nullable = true, precision = 2)
    public Long getTienDukien() {
        return tienDukien;
    }

    public void setTienDukien(Long tienDukien) {
        this.tienDukien = tienDukien;
    }

    @Basic
    @Column(name = "TIEN_HOANTHANH", nullable = true, precision = 2)
    public Long getTienHoanthanh() {
        return tienHoanthanh;
    }

    public void setTienHoanthanh(Long tienHoanthanh) {
        this.tienHoanthanh = tienHoanthanh;
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
    @Column(name = "NGUOI_CN", nullable = false, precision = 0)
    public int getNguoiCn() {
        return nguoiCn;
    }

    public void setNguoiCn(int nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    @Basic
    @Column(name = "NGAY_CN", nullable = false)
    public Time getNgayCn() {
        return ngayCn;
    }

    public void setNgayCn(Time ngayCn) {
        this.ngayCn = ngayCn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NckhDutoan that = (NckhDutoan) o;

        if (dutoanid != that.dutoanid) return false;
        if (loaidutoanid != that.loaidutoanid) return false;
        if (detaiid != that.detaiid) return false;
        if (nguoiCn != that.nguoiCn) return false;
        if (tienDukien != null ? !tienDukien.equals(that.tienDukien) : that.tienDukien != null) return false;
        if (tienHoanthanh != null ? !tienHoanthanh.equals(that.tienHoanthanh) : that.tienHoanthanh != null)
            return false;
        if (ghichu != null ? !ghichu.equals(that.ghichu) : that.ghichu != null) return false;
        if (ngayCn != null ? !ngayCn.equals(that.ngayCn) : that.ngayCn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dutoanid;
        result = 31 * result + (int) loaidutoanid;
        result = 31 * result + (tienDukien != null ? tienDukien.hashCode() : 0);
        result = 31 * result + (tienHoanthanh != null ? tienHoanthanh.hashCode() : 0);
        result = 31 * result + detaiid;
        result = 31 * result + (ghichu != null ? ghichu.hashCode() : 0);
        result = 31 * result + nguoiCn;
        result = 31 * result + (ngayCn != null ? ngayCn.hashCode() : 0);
        return result;
    }
}
