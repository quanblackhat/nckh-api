package com.vnptit.vnpthis.domain.nckh;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NCKH_DETAI", schema = "PM2_FRESHER", catalog = "")
public class NckhDetai {
    @Id
    private int detaiid;
    private Integer sott;
    private String tendetai;
    private Time ngaytao;
    private Time ngaypheduyet;
    private Time ngaybd;
    private Time ngaykt;
    private Long hientrang;
    private Long xuatban;
    private Long capquanly;
    private Time ngaynghiemthu;
    private Long kinhphithuchien;
    private String nguonkinhphi;
    private int chuyenmucid;
    private int csytid;
    private String muctieu;
    private Long kinhphiDukien;
    private Long chunhiemdetai;
    private String noidungtongquan;
    private Byte trangthaiid;
    private Long kinhphiHoanthanh;
    private Byte xeploaiid;
    private Long tongdiem;
    private String ghichu;
    private int nguoiCn;
    private Time ngayCn;

    @Basic
    @Column(name = "DETAIID", nullable = false, precision = 0)
    public int getDetaiid() {
        return detaiid;
    }

    public void setDetaiid(int detaiid) {
        this.detaiid = detaiid;
    }

    @Basic
    @Column(name = "SOTT", nullable = true, precision = 0)
    public Integer getSott() {
        return sott;
    }

    public void setSott(Integer sott) {
        this.sott = sott;
    }

    @Basic
    @Column(name = "TENDETAI", nullable = false, length = 255)
    public String getTendetai() {
        return tendetai;
    }

    public void setTendetai(String tendetai) {
        this.tendetai = tendetai;
    }

    @Basic
    @Column(name = "NGAYTAO", nullable = false)
    public Time getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Time ngaytao) {
        this.ngaytao = ngaytao;
    }

    @Basic
    @Column(name = "NGAYPHEDUYET", nullable = true)
    public Time getNgaypheduyet() {
        return ngaypheduyet;
    }

    public void setNgaypheduyet(Time ngaypheduyet) {
        this.ngaypheduyet = ngaypheduyet;
    }

    @Basic
    @Column(name = "NGAYBD", nullable = true)
    public Time getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(Time ngaybd) {
        this.ngaybd = ngaybd;
    }

    @Basic
    @Column(name = "NGAYKT", nullable = true)
    public Time getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(Time ngaykt) {
        this.ngaykt = ngaykt;
    }

    @Basic
    @Column(name = "HIENTRANG", nullable = true, precision = 0)
    public Long getHientrang() {
        return hientrang;
    }

    public void setHientrang(Long hientrang) {
        this.hientrang = hientrang;
    }

    @Basic
    @Column(name = "XUATBAN", nullable = true, precision = 0)
    public Long getXuatban() {
        return xuatban;
    }

    public void setXuatban(Long xuatban) {
        this.xuatban = xuatban;
    }

    @Basic
    @Column(name = "CAPQUANLY", nullable = true, precision = 0)
    public Long getCapquanly() {
        return capquanly;
    }

    public void setCapquanly(Long capquanly) {
        this.capquanly = capquanly;
    }

    @Basic
    @Column(name = "NGAYNGHIEMTHU", nullable = true)
    public Time getNgaynghiemthu() {
        return ngaynghiemthu;
    }

    public void setNgaynghiemthu(Time ngaynghiemthu) {
        this.ngaynghiemthu = ngaynghiemthu;
    }

    @Basic
    @Column(name = "KINHPHITHUCHIEN", nullable = true, precision = 0)
    public Long getKinhphithuchien() {
        return kinhphithuchien;
    }

    public void setKinhphithuchien(Long kinhphithuchien) {
        this.kinhphithuchien = kinhphithuchien;
    }

    @Basic
    @Column(name = "NGUONKINHPHI", nullable = true, length = 255)
    public String getNguonkinhphi() {
        return nguonkinhphi;
    }

    public void setNguonkinhphi(String nguonkinhphi) {
        this.nguonkinhphi = nguonkinhphi;
    }

    @Basic
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
    @Column(name = "MUCTIEU", nullable = true, length = 255)
    public String getMuctieu() {
        return muctieu;
    }

    public void setMuctieu(String muctieu) {
        this.muctieu = muctieu;
    }

    @Basic
    @Column(name = "KINHPHI_DUKIEN", nullable = true, precision = 2)
    public Long getKinhphiDukien() {
        return kinhphiDukien;
    }

    public void setKinhphiDukien(Long kinhphiDukien) {
        this.kinhphiDukien = kinhphiDukien;
    }

    @Basic
    @Column(name = "CHUNHIEMDETAI", nullable = true, precision = 0)
    public Long getChunhiemdetai() {
        return chunhiemdetai;
    }

    public void setChunhiemdetai(Long chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
    }

    @Basic
    @Column(name = "NOIDUNGTONGQUAN", nullable = true)
    public String getNoidungtongquan() {
        return noidungtongquan;
    }

    public void setNoidungtongquan(String noidungtongquan) {
        this.noidungtongquan = noidungtongquan;
    }

    @Basic
    @Column(name = "TRANGTHAIID", nullable = true, precision = 0)
    public Byte getTrangthaiid() {
        return trangthaiid;
    }

    public void setTrangthaiid(Byte trangthaiid) {
        this.trangthaiid = trangthaiid;
    }

    @Basic
    @Column(name = "KINHPHI_HOANTHANH", nullable = true, precision = 2)
    public Long getKinhphiHoanthanh() {
        return kinhphiHoanthanh;
    }

    public void setKinhphiHoanthanh(Long kinhphiHoanthanh) {
        this.kinhphiHoanthanh = kinhphiHoanthanh;
    }

    @Basic
    @Column(name = "XEPLOAIID", nullable = true, precision = 0)
    public Byte getXeploaiid() {
        return xeploaiid;
    }

    public void setXeploaiid(Byte xeploaiid) {
        this.xeploaiid = xeploaiid;
    }

    @Basic
    @Column(name = "TONGDIEM", nullable = true, precision = 2)
    public Long getTongdiem() {
        return tongdiem;
    }

    public void setTongdiem(Long tongdiem) {
        this.tongdiem = tongdiem;
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

        NckhDetai nckhDetai = (NckhDetai) o;

        if (detaiid != nckhDetai.detaiid) return false;
        if (chuyenmucid != nckhDetai.chuyenmucid) return false;
        if (csytid != nckhDetai.csytid) return false;
        if (nguoiCn != nckhDetai.nguoiCn) return false;
        if (sott != null ? !sott.equals(nckhDetai.sott) : nckhDetai.sott != null) return false;
        if (tendetai != null ? !tendetai.equals(nckhDetai.tendetai) : nckhDetai.tendetai != null) return false;
        if (ngaytao != null ? !ngaytao.equals(nckhDetai.ngaytao) : nckhDetai.ngaytao != null) return false;
        if (ngaypheduyet != null ? !ngaypheduyet.equals(nckhDetai.ngaypheduyet) : nckhDetai.ngaypheduyet != null)
            return false;
        if (ngaybd != null ? !ngaybd.equals(nckhDetai.ngaybd) : nckhDetai.ngaybd != null) return false;
        if (ngaykt != null ? !ngaykt.equals(nckhDetai.ngaykt) : nckhDetai.ngaykt != null) return false;
        if (hientrang != null ? !hientrang.equals(nckhDetai.hientrang) : nckhDetai.hientrang != null) return false;
        if (xuatban != null ? !xuatban.equals(nckhDetai.xuatban) : nckhDetai.xuatban != null) return false;
        if (capquanly != null ? !capquanly.equals(nckhDetai.capquanly) : nckhDetai.capquanly != null) return false;
        if (ngaynghiemthu != null ? !ngaynghiemthu.equals(nckhDetai.ngaynghiemthu) : nckhDetai.ngaynghiemthu != null)
            return false;
        if (kinhphithuchien != null ? !kinhphithuchien.equals(nckhDetai.kinhphithuchien) : nckhDetai.kinhphithuchien != null)
            return false;
        if (nguonkinhphi != null ? !nguonkinhphi.equals(nckhDetai.nguonkinhphi) : nckhDetai.nguonkinhphi != null)
            return false;
        if (muctieu != null ? !muctieu.equals(nckhDetai.muctieu) : nckhDetai.muctieu != null) return false;
        if (kinhphiDukien != null ? !kinhphiDukien.equals(nckhDetai.kinhphiDukien) : nckhDetai.kinhphiDukien != null)
            return false;
        if (chunhiemdetai != null ? !chunhiemdetai.equals(nckhDetai.chunhiemdetai) : nckhDetai.chunhiemdetai != null)
            return false;
        if (noidungtongquan != null ? !noidungtongquan.equals(nckhDetai.noidungtongquan) : nckhDetai.noidungtongquan != null)
            return false;
        if (trangthaiid != null ? !trangthaiid.equals(nckhDetai.trangthaiid) : nckhDetai.trangthaiid != null)
            return false;
        if (kinhphiHoanthanh != null ? !kinhphiHoanthanh.equals(nckhDetai.kinhphiHoanthanh) : nckhDetai.kinhphiHoanthanh != null)
            return false;
        if (xeploaiid != null ? !xeploaiid.equals(nckhDetai.xeploaiid) : nckhDetai.xeploaiid != null) return false;
        if (tongdiem != null ? !tongdiem.equals(nckhDetai.tongdiem) : nckhDetai.tongdiem != null) return false;
        if (ghichu != null ? !ghichu.equals(nckhDetai.ghichu) : nckhDetai.ghichu != null) return false;
        if (ngayCn != null ? !ngayCn.equals(nckhDetai.ngayCn) : nckhDetai.ngayCn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = detaiid;
        result = 31 * result + (sott != null ? sott.hashCode() : 0);
        result = 31 * result + (tendetai != null ? tendetai.hashCode() : 0);
        result = 31 * result + (ngaytao != null ? ngaytao.hashCode() : 0);
        result = 31 * result + (ngaypheduyet != null ? ngaypheduyet.hashCode() : 0);
        result = 31 * result + (ngaybd != null ? ngaybd.hashCode() : 0);
        result = 31 * result + (ngaykt != null ? ngaykt.hashCode() : 0);
        result = 31 * result + (hientrang != null ? hientrang.hashCode() : 0);
        result = 31 * result + (xuatban != null ? xuatban.hashCode() : 0);
        result = 31 * result + (capquanly != null ? capquanly.hashCode() : 0);
        result = 31 * result + (ngaynghiemthu != null ? ngaynghiemthu.hashCode() : 0);
        result = 31 * result + (kinhphithuchien != null ? kinhphithuchien.hashCode() : 0);
        result = 31 * result + (nguonkinhphi != null ? nguonkinhphi.hashCode() : 0);
        result = 31 * result + chuyenmucid;
        result = 31 * result + csytid;
        result = 31 * result + (muctieu != null ? muctieu.hashCode() : 0);
        result = 31 * result + (kinhphiDukien != null ? kinhphiDukien.hashCode() : 0);
        result = 31 * result + (chunhiemdetai != null ? chunhiemdetai.hashCode() : 0);
        result = 31 * result + (noidungtongquan != null ? noidungtongquan.hashCode() : 0);
        result = 31 * result + (trangthaiid != null ? trangthaiid.hashCode() : 0);
        result = 31 * result + (kinhphiHoanthanh != null ? kinhphiHoanthanh.hashCode() : 0);
        result = 31 * result + (xeploaiid != null ? xeploaiid.hashCode() : 0);
        result = 31 * result + (tongdiem != null ? tongdiem.hashCode() : 0);
        result = 31 * result + (ghichu != null ? ghichu.hashCode() : 0);
        result = 31 * result + nguoiCn;
        result = 31 * result + (ngayCn != null ? ngayCn.hashCode() : 0);
        return result;
    }
}
