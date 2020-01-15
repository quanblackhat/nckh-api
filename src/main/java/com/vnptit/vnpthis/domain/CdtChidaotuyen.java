package com.vnptit.vnpthis.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CDT_CHIDAOTUYEN", schema = "PM2_FRESHER", catalog = "")
public class CdtChidaotuyen {
    @Id
    private int chidaotuyenid;
    private String soquyetdinh;
    private Time ngayquyetdinh;
    private String sohopdong;
    private Time ngayhopdong;
    private String ghichu;
    private int lydocongtacid;
    private String noidung;
    private Time ngaybd;
    private Time ngaykt;
    private Time ngaytao;
    private Integer sobenhnhankham;
    private Integer sobenhnhankythuat;
    private Integer socanbochuyengiao;
    private int ketquacongtacid;

    @ManyToMany
    @JoinTable(
        name = "CDT_KYTHUAT",
        joinColumns = {@JoinColumn(name = "chidaotuyenid")},
        inverseJoinColumns = {@JoinColumn(name = "kythuathotroid")}
    )
    Set<CdtKythuathotro> danhSachKyThuatHoTro = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "CDT_VATTU",
        joinColumns = {@JoinColumn(name = "chidaotuyenid")},
        inverseJoinColumns = {@JoinColumn(name = "vattuhotroid")}
    )
    Set<CdtVattuhotro> danhSachVatTuHoTro = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "CDT_NOIDEN",
        joinColumns = {@JoinColumn(name = "chidaotuyenid")},
        inverseJoinColumns = {@JoinColumn(name = "noidencongtacid")}
    )
    Set<CdtNoidencongtac> danhSachNoiDen = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "lydocongtacid", insertable = false, updatable = false)
    private CdtLydocongtac lydocongtac;

    @ManyToOne
    @JoinColumn(name = "ketquacongtacid", insertable = false, updatable = false)
    private CdtKetquacongtac ketquacongtac;


    public Set<CdtKythuathotro> getDanhSachKyThuatHoTro() {
        return danhSachKyThuatHoTro;
    }

    @Basic
    @Column(name = "CHIDAOTUYENID", nullable = false, precision = 0)
    public int getChidaotuyenid() {
        return chidaotuyenid;
    }

    public void setChidaotuyenid(int chidaotuyenid) {
        this.chidaotuyenid = chidaotuyenid;
    }

    @Basic
    @Column(name = "SOQUYETDINH", nullable = true, length = 200)
    public String getSoquyetdinh() {
        return soquyetdinh;
    }

    public void setSoquyetdinh(String soquyetdinh) {
        this.soquyetdinh = soquyetdinh;
    }

    @Basic
    @Column(name = "NGAYQUYETDINH", nullable = true)
    public Time getNgayquyetdinh() {
        return ngayquyetdinh;
    }

    public void setNgayquyetdinh(Time ngayquyetdinh) {
        this.ngayquyetdinh = ngayquyetdinh;
    }

    @Basic
    @Column(name = "SOHOPDONG", nullable = true, length = 200)
    public String getSohopdong() {
        return sohopdong;
    }

    public void setSohopdong(String sohopdong) {
        this.sohopdong = sohopdong;
    }

    @Basic
    @Column(name = "NGAYHOPDONG", nullable = true)
    public Time getNgayhopdong() {
        return ngayhopdong;
    }

    public void setNgayhopdong(Time ngayhopdong) {
        this.ngayhopdong = ngayhopdong;
    }

    @Basic
    @Column(name = "GHICHU", nullable = true, length = 2000)
    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Basic
    @Column(name = "LYDOCONGTACID", nullable = false, precision = 0)
    public int getLydocongtacid() {
        return lydocongtacid;
    }

    public void setLydocongtacid(int lydocongtacid) {
        this.lydocongtacid = lydocongtacid;
    }

    @Basic
    @Column(name = "NOIDUNG", nullable = true, length = 2000)
    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
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
    @Column(name = "NGAYTAO", nullable = true)
    public Time getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Time ngaytao) {
        this.ngaytao = ngaytao;
    }

    @Basic
    @Column(name = "SOBENHNHANKHAM", nullable = true, precision = 0)
    public Integer getSobenhnhankham() {
        return sobenhnhankham;
    }

    public void setSobenhnhankham(Integer sobenhnhankham) {
        this.sobenhnhankham = sobenhnhankham;
    }

    @Basic
    @Column(name = "SOBENHNHANKYTHUAT", nullable = true, precision = 0)
    public Integer getSobenhnhankythuat() {
        return sobenhnhankythuat;
    }

    public void setSobenhnhankythuat(Integer sobenhnhankythuat) {
        this.sobenhnhankythuat = sobenhnhankythuat;
    }

    @Basic
    @Column(name = "SOCANBOCHUYENGIAO", nullable = true, precision = 0)
    public Integer getSocanbochuyengiao() {
        return socanbochuyengiao;
    }

    public void setSocanbochuyengiao(Integer socanbochuyengiao) {
        this.socanbochuyengiao = socanbochuyengiao;
    }

    @Basic
    @Column(name = "KETQUACONGTACID", nullable = false, precision = 0)
    public int getKetquacongtacid() {
        return ketquacongtacid;
    }

    public void setKetquacongtacid(int ketquacongtacid) {
        this.ketquacongtacid = ketquacongtacid;
    }

    public void setDanhSachKyThuatHoTro(Set<CdtKythuathotro> danhSachKyThuatHoTro) {
        this.danhSachKyThuatHoTro = danhSachKyThuatHoTro;
    }

    public Set<CdtVattuhotro> getDanhSachVatTuHoTro() {
        return danhSachVatTuHoTro;
    }

    public void setDanhSachVatTuHoTro(Set<CdtVattuhotro> danhSachVatTuHoTro) {
        this.danhSachVatTuHoTro = danhSachVatTuHoTro;
    }

    public Set<CdtNoidencongtac> getDanhSachNoiDen() {
        return danhSachNoiDen;
    }

    public void setDanhSachNoiDen(Set<CdtNoidencongtac> danhSachNoiDen) {
        this.danhSachNoiDen = danhSachNoiDen;
    }

    public CdtLydocongtac getLydocongtac() {
        return lydocongtac;
    }

    public void setLydocongtac(CdtLydocongtac lydocongtac) {
        this.lydocongtac = lydocongtac;
    }

    public CdtKetquacongtac getKetquacongtac() {
        return ketquacongtac;
    }

    public void setKetquacongtac(CdtKetquacongtac ketquacongtac) {
        this.ketquacongtac = ketquacongtac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdtChidaotuyen that = (CdtChidaotuyen) o;

        if (chidaotuyenid != that.chidaotuyenid) return false;
        if (lydocongtacid != that.lydocongtacid) return false;
        if (ketquacongtacid != that.ketquacongtacid) return false;
        if (soquyetdinh != null ? !soquyetdinh.equals(that.soquyetdinh) : that.soquyetdinh != null) return false;
        if (ngayquyetdinh != null ? !ngayquyetdinh.equals(that.ngayquyetdinh) : that.ngayquyetdinh != null)
            return false;
        if (sohopdong != null ? !sohopdong.equals(that.sohopdong) : that.sohopdong != null) return false;
        if (ngayhopdong != null ? !ngayhopdong.equals(that.ngayhopdong) : that.ngayhopdong != null) return false;
        if (ghichu != null ? !ghichu.equals(that.ghichu) : that.ghichu != null) return false;
        if (noidung != null ? !noidung.equals(that.noidung) : that.noidung != null) return false;
        if (ngaybd != null ? !ngaybd.equals(that.ngaybd) : that.ngaybd != null) return false;
        if (ngaykt != null ? !ngaykt.equals(that.ngaykt) : that.ngaykt != null) return false;
        if (ngaytao != null ? !ngaytao.equals(that.ngaytao) : that.ngaytao != null) return false;
        if (sobenhnhankham != null ? !sobenhnhankham.equals(that.sobenhnhankham) : that.sobenhnhankham != null)
            return false;
        if (sobenhnhankythuat != null ? !sobenhnhankythuat.equals(that.sobenhnhankythuat) : that.sobenhnhankythuat != null)
            return false;
        if (socanbochuyengiao != null ? !socanbochuyengiao.equals(that.socanbochuyengiao) : that.socanbochuyengiao != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chidaotuyenid;
        result = 31 * result + (soquyetdinh != null ? soquyetdinh.hashCode() : 0);
        result = 31 * result + (ngayquyetdinh != null ? ngayquyetdinh.hashCode() : 0);
        result = 31 * result + (sohopdong != null ? sohopdong.hashCode() : 0);
        result = 31 * result + (ngayhopdong != null ? ngayhopdong.hashCode() : 0);
        result = 31 * result + (ghichu != null ? ghichu.hashCode() : 0);
        result = 31 * result + lydocongtacid;
        result = 31 * result + (noidung != null ? noidung.hashCode() : 0);
        result = 31 * result + (ngaybd != null ? ngaybd.hashCode() : 0);
        result = 31 * result + (ngaykt != null ? ngaykt.hashCode() : 0);
        result = 31 * result + (ngaytao != null ? ngaytao.hashCode() : 0);
        result = 31 * result + (sobenhnhankham != null ? sobenhnhankham.hashCode() : 0);
        result = 31 * result + (sobenhnhankythuat != null ? sobenhnhankythuat.hashCode() : 0);
        result = 31 * result + (socanbochuyengiao != null ? socanbochuyengiao.hashCode() : 0);
        result = 31 * result + ketquacongtacid;
        return result;
    }
}
