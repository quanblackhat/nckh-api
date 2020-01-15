package com.vnptit.vnpthis.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.Arrays;

@Entity
@Table(name = "NCKH_UPFILE", schema = "PM2_FRESHER", catalog = "")
public class NckhUpfile {
    @Id
    private int fileid;
    private String mota;
    private Integer detaiid;
    private byte[] noidung;
    private int nguoiCn;
    private Time ngayCn;
    private Integer tiendoid;

    @Basic
    @Column(name = "FILEID", nullable = false, precision = 0)
    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    @Basic
    @Column(name = "MOTA", nullable = true, length = 255)
    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @Basic
    @Column(name = "DETAIID", nullable = true, precision = 0)
    public Integer getDetaiid() {
        return detaiid;
    }

    public void setDetaiid(Integer detaiid) {
        this.detaiid = detaiid;
    }

    @Basic
    @Column(name = "NOIDUNG", nullable = false)
    public byte[] getNoidung() {
        return noidung;
    }

    public void setNoidung(byte[] noidung) {
        this.noidung = noidung;
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

    @Basic
    @Column(name = "TIENDOID", nullable = true, precision = 0)
    public Integer getTiendoid() {
        return tiendoid;
    }

    public void setTiendoid(Integer tiendoid) {
        this.tiendoid = tiendoid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NckhUpfile that = (NckhUpfile) o;

        if (fileid != that.fileid) return false;
        if (nguoiCn != that.nguoiCn) return false;
        if (mota != null ? !mota.equals(that.mota) : that.mota != null) return false;
        if (detaiid != null ? !detaiid.equals(that.detaiid) : that.detaiid != null) return false;
        if (!Arrays.equals(noidung, that.noidung)) return false;
        if (ngayCn != null ? !ngayCn.equals(that.ngayCn) : that.ngayCn != null) return false;
        if (tiendoid != null ? !tiendoid.equals(that.tiendoid) : that.tiendoid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileid;
        result = 31 * result + (mota != null ? mota.hashCode() : 0);
        result = 31 * result + (detaiid != null ? detaiid.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(noidung);
        result = 31 * result + nguoiCn;
        result = 31 * result + (ngayCn != null ? ngayCn.hashCode() : 0);
        result = 31 * result + (tiendoid != null ? tiendoid.hashCode() : 0);
        return result;
    }
}
