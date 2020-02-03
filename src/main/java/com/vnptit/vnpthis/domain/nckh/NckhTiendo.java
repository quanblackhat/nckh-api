package com.vnptit.vnpthis.domain.nckh;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NCKH_TIENDO", schema = "PM2_FRESHER", catalog = "")
public class NckhTiendo {
    @Id
    private int tiendoid;
    private long tyleHoanthanh;
    private int detaiid;
    private String mota;
    private int nguoiCn;
    private Time ngayCn;

    @Basic
    @Column(name = "TIENDOID", nullable = false, precision = 0)
    public int getTiendoid() {
        return tiendoid;
    }

    public void setTiendoid(int tiendoid) {
        this.tiendoid = tiendoid;
    }

    @Basic
    @Column(name = "TYLE_HOANTHANH", nullable = false, precision = 2)
    public long getTyleHoanthanh() {
        return tyleHoanthanh;
    }

    public void setTyleHoanthanh(long tyleHoanthanh) {
        this.tyleHoanthanh = tyleHoanthanh;
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
    @Column(name = "MOTA", nullable = true, length = 255)
    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
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

        NckhTiendo that = (NckhTiendo) o;

        if (tiendoid != that.tiendoid) return false;
        if (tyleHoanthanh != that.tyleHoanthanh) return false;
        if (detaiid != that.detaiid) return false;
        if (nguoiCn != that.nguoiCn) return false;
        if (mota != null ? !mota.equals(that.mota) : that.mota != null) return false;
        if (ngayCn != null ? !ngayCn.equals(that.ngayCn) : that.ngayCn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tiendoid;
        result = 31 * result + (int) (tyleHoanthanh ^ (tyleHoanthanh >>> 32));
        result = 31 * result + detaiid;
        result = 31 * result + (mota != null ? mota.hashCode() : 0);
        result = 31 * result + nguoiCn;
        result = 31 * result + (ngayCn != null ? ngayCn.hashCode() : 0);
        return result;
    }
}
