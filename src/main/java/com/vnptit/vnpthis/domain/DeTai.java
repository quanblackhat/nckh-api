package com.vnptit.vnpthis.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A DeTai.
 */
@Entity
@Table(name = "NCKH_DETAI")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeTai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "detaiid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "sott", nullable = false)
    private Integer sott;

    @NotNull
    @Column(name = "tendetai", nullable = false)
    private String tendetai;

    @NotNull
    @Column(name = "ngaytao", nullable = false)
    private LocalDate ngaytao;

    @NotNull
    @Column(name = "ngaypheduyet", nullable = false)
    private LocalDate ngaypheduyet;

    @NotNull
    @Column(name = "ngaybd", nullable = false)
    private LocalDate ngaybd;

    @NotNull
    @Column(name = "ngaykt", nullable = false)
    private LocalDate ngaykt;

    @NotNull
    @Column(name = "hientrang", nullable = false)
    private Integer hientrang;

    @NotNull
    @Column(name = "xuatban", nullable = false)
    private Integer xuatban;

    @NotNull
    @Column(name = "capquanly", nullable = false)
    private Integer capquanly;

    @NotNull
    @Column(name = "ngaynghiemthu", nullable = false)
    private LocalDate ngaynghiemthu;

    @NotNull
    @Column(name = "kinhphithuchien", nullable = false)
    private Integer kinhphithuchien;

    @NotNull
    @Column(name = "nguonkinhphi", nullable = false)
    private String nguonkinhphi;

    @NotNull
    @Column(name = "muctieu", nullable = false)
    private String muctieu;

    @NotNull
    @Column(name = "kinhphi_dukien", nullable = false)
    private Integer kinhphiDukien;

    @NotNull
    @Column(name = "chunhiemdetai", nullable = false)
    private Integer chunhiemdetai;

    @NotNull
    @Column(name = "noidungtongquan", nullable = false)
    private String noidungtongquan;

    @NotNull
    @Column(name = "kinhphi_hoanthanh", nullable = false)
    private Integer kinhphiHoanthanh;

    @NotNull
    @Column(name = "tongdiem", nullable = false)
    private Integer tongdiem;

    @NotNull
    @Column(name = "ghichu", nullable = false)
    private String ghichu;

    @NotNull
    @Column(name = "nguoi_cn", nullable = false)
    private Integer nguoiCn;

    @NotNull
    @Column(name = "ngay_cn", nullable = false)
    private LocalDate ngayCn;

    @OneToMany(mappedBy = "deTai", cascade = CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UpFile> upFiles = new HashSet<>();

    @OneToMany(mappedBy = "deTai", cascade = CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TienDo> tienDos = new HashSet<>();

    @OneToMany(mappedBy = "deTai", cascade = CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NhanSu> nhanSus = new HashSet<>();

    @OneToMany(mappedBy = "deTai", cascade = CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DuToan> duToans = new HashSet<>();

    @OneToMany(mappedBy = "deTai", cascade = CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DanhGia> danhGias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "chuyenmucid", insertable = false, updatable = false)
    @JsonIgnoreProperties("deTais")
    private ChuyenMuc chuyenMuc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSott() {
        return sott;
    }

    public DeTai sott(Integer sott) {
        this.sott = sott;
        return this;
    }

    public void setSott(Integer sott) {
        this.sott = sott;
    }

    public String getTendetai() {
        return tendetai;
    }

    public DeTai tendetai(String tendetai) {
        this.tendetai = tendetai;
        return this;
    }

    public void setTendetai(String tendetai) {
        this.tendetai = tendetai;
    }

    public LocalDate getNgaytao() {
        return ngaytao;
    }

    public DeTai ngaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
        return this;
    }

    public void setNgaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
    }

    public LocalDate getNgaypheduyet() {
        return ngaypheduyet;
    }

    public DeTai ngaypheduyet(LocalDate ngaypheduyet) {
        this.ngaypheduyet = ngaypheduyet;
        return this;
    }

    public void setNgaypheduyet(LocalDate ngaypheduyet) {
        this.ngaypheduyet = ngaypheduyet;
    }

    public LocalDate getNgaybd() {
        return ngaybd;
    }

    public DeTai ngaybd(LocalDate ngaybd) {
        this.ngaybd = ngaybd;
        return this;
    }

    public void setNgaybd(LocalDate ngaybd) {
        this.ngaybd = ngaybd;
    }

    public LocalDate getNgaykt() {
        return ngaykt;
    }

    public DeTai ngaykt(LocalDate ngaykt) {
        this.ngaykt = ngaykt;
        return this;
    }

    public void setNgaykt(LocalDate ngaykt) {
        this.ngaykt = ngaykt;
    }

    public Integer getHientrang() {
        return hientrang;
    }

    public DeTai hientrang(Integer hientrang) {
        this.hientrang = hientrang;
        return this;
    }

    public void setHientrang(Integer hientrang) {
        this.hientrang = hientrang;
    }

    public Integer getXuatban() {
        return xuatban;
    }

    public DeTai xuatban(Integer xuatban) {
        this.xuatban = xuatban;
        return this;
    }

    public void setXuatban(Integer xuatban) {
        this.xuatban = xuatban;
    }

    public Integer getCapquanly() {
        return capquanly;
    }

    public DeTai capquanly(Integer capquanly) {
        this.capquanly = capquanly;
        return this;
    }

    public void setCapquanly(Integer capquanly) {
        this.capquanly = capquanly;
    }

    public LocalDate getNgaynghiemthu() {
        return ngaynghiemthu;
    }

    public DeTai ngaynghiemthu(LocalDate ngaynghiemthu) {
        this.ngaynghiemthu = ngaynghiemthu;
        return this;
    }

    public void setNgaynghiemthu(LocalDate ngaynghiemthu) {
        this.ngaynghiemthu = ngaynghiemthu;
    }

    public Integer getKinhphithuchien() {
        return kinhphithuchien;
    }

    public DeTai kinhphithuchien(Integer kinhphithuchien) {
        this.kinhphithuchien = kinhphithuchien;
        return this;
    }

    public void setKinhphithuchien(Integer kinhphithuchien) {
        this.kinhphithuchien = kinhphithuchien;
    }

    public String getNguonkinhphi() {
        return nguonkinhphi;
    }

    public DeTai nguonkinhphi(String nguonkinhphi) {
        this.nguonkinhphi = nguonkinhphi;
        return this;
    }

    public void setNguonkinhphi(String nguonkinhphi) {
        this.nguonkinhphi = nguonkinhphi;
    }

    public String getMuctieu() {
        return muctieu;
    }

    public DeTai muctieu(String muctieu) {
        this.muctieu = muctieu;
        return this;
    }

    public void setMuctieu(String muctieu) {
        this.muctieu = muctieu;
    }

    public Integer getKinhphiDukien() {
        return kinhphiDukien;
    }

    public DeTai kinhphiDukien(Integer kinhphiDukien) {
        this.kinhphiDukien = kinhphiDukien;
        return this;
    }

    public void setKinhphiDukien(Integer kinhphiDukien) {
        this.kinhphiDukien = kinhphiDukien;
    }

    public Integer getChunhiemdetai() {
        return chunhiemdetai;
    }

    public DeTai chunhiemdetai(Integer chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
        return this;
    }

    public void setChunhiemdetai(Integer chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
    }

    public String getNoidungtongquan() {
        return noidungtongquan;
    }

    public DeTai noidungtongquan(String noidungtongquan) {
        this.noidungtongquan = noidungtongquan;
        return this;
    }

    public void setNoidungtongquan(String noidungtongquan) {
        this.noidungtongquan = noidungtongquan;
    }

    public Integer getKinhphiHoanthanh() {
        return kinhphiHoanthanh;
    }

    public DeTai kinhphiHoanthanh(Integer kinhphiHoanthanh) {
        this.kinhphiHoanthanh = kinhphiHoanthanh;
        return this;
    }

    public void setKinhphiHoanthanh(Integer kinhphiHoanthanh) {
        this.kinhphiHoanthanh = kinhphiHoanthanh;
    }

    public Integer getTongdiem() {
        return tongdiem;
    }

    public DeTai tongdiem(Integer tongdiem) {
        this.tongdiem = tongdiem;
        return this;
    }

    public void setTongdiem(Integer tongdiem) {
        this.tongdiem = tongdiem;
    }

    public String getGhichu() {
        return ghichu;
    }

    public DeTai ghichu(String ghichu) {
        this.ghichu = ghichu;
        return this;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Integer getNguoiCn() {
        return nguoiCn;
    }

    public DeTai nguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
        return this;
    }

    public void setNguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public LocalDate getNgayCn() {
        return ngayCn;
    }

    public DeTai ngayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
        return this;
    }

    public void setNgayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
    }

    public Set<UpFile> getUpFiles() {
        return upFiles;
    }

    public DeTai upFiles(Set<UpFile> upFiles) {
        this.upFiles = upFiles;
        return this;
    }

    public DeTai addUpFile(UpFile upFile) {
        this.upFiles.add(upFile);
        upFile.setDeTai(this);
        return this;
    }

    public DeTai removeUpFile(UpFile upFile) {
        this.upFiles.remove(upFile);
        upFile.setDeTai(null);
        return this;
    }

    public void setUpFiles(Set<UpFile> upFiles) {
        this.upFiles = upFiles;
    }

    public Set<TienDo> getTienDos() {
        return tienDos;
    }

    public DeTai tienDos(Set<TienDo> tienDos) {
        this.tienDos = tienDos;
        return this;
    }

    public DeTai addTienDo(TienDo tienDo) {
        this.tienDos.add(tienDo);
        tienDo.setDeTai(this);
        return this;
    }

    public DeTai removeTienDo(TienDo tienDo) {
        this.tienDos.remove(tienDo);
        tienDo.setDeTai(null);
        return this;
    }

    public void setTienDos(Set<TienDo> tienDos) {
        this.tienDos = tienDos;
    }

    public Set<NhanSu> getNhanSus() {
        return nhanSus;
    }

    public DeTai nhanSus(Set<NhanSu> nhanSus) {
        this.nhanSus = nhanSus;
        return this;
    }

    public DeTai addNhanSu(NhanSu nhanSu) {
        this.nhanSus.add(nhanSu);
        nhanSu.setDeTai(this);
        return this;
    }

    public DeTai removeNhanSu(NhanSu nhanSu) {
        this.nhanSus.remove(nhanSu);
        nhanSu.setDeTai(null);
        return this;
    }

    public void setNhanSus(Set<NhanSu> nhanSus) {
        this.nhanSus = nhanSus;
    }

    public Set<DuToan> getDuToans() {
        return duToans;
    }

    public DeTai duToans(Set<DuToan> duToans) {
        this.duToans = duToans;
        return this;
    }

    public DeTai addDuToan(DuToan duToan) {
        this.duToans.add(duToan);
        duToan.setDeTai(this);
        return this;
    }

    public DeTai removeDuToan(DuToan duToan) {
        this.duToans.remove(duToan);
        duToan.setDeTai(null);
        return this;
    }

    public void setDuToans(Set<DuToan> duToans) {
        this.duToans = duToans;
    }

    public Set<DanhGia> getDanhGias() {
        return danhGias;
    }

    public DeTai danhGias(Set<DanhGia> danhGias) {
        this.danhGias = danhGias;
        return this;
    }

    public DeTai addDanhGia(DanhGia danhGia) {
        this.danhGias.add(danhGia);
        danhGia.setDeTai(this);
        return this;
    }

    public DeTai removeDanhGia(DanhGia danhGia) {
        this.danhGias.remove(danhGia);
        danhGia.setDeTai(null);
        return this;
    }

    public void setDanhGias(Set<DanhGia> danhGias) {
        this.danhGias = danhGias;
    }

    public ChuyenMuc getChuyenMuc() {
        return chuyenMuc;
    }

    public DeTai chuyenMuc(ChuyenMuc chuyenMuc) {
        this.chuyenMuc = chuyenMuc;
        return this;
    }

    public void setChuyenMuc(ChuyenMuc chuyenMuc) {
        this.chuyenMuc = chuyenMuc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeTai)) {
            return false;
        }
        return id != null && id.equals(((DeTai) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DeTai{" +
            "id=" + getId() +
            ", sott=" + getSott() +
            ", tendetai='" + getTendetai() + "'" +
            ", ngaytao='" + getNgaytao() + "'" +
            ", ngaypheduyet='" + getNgaypheduyet() + "'" +
            ", ngaybd='" + getNgaybd() + "'" +
            ", ngaykt='" + getNgaykt() + "'" +
            ", hientrang=" + getHientrang() +
            ", xuatban=" + getXuatban() +
            ", capquanly=" + getCapquanly() +
            ", ngaynghiemthu='" + getNgaynghiemthu() + "'" +
            ", kinhphithuchien=" + getKinhphithuchien() +
            ", nguonkinhphi='" + getNguonkinhphi() + "'" +
            ", muctieu='" + getMuctieu() + "'" +
            ", kinhphiDukien=" + getKinhphiDukien() +
            ", chunhiemdetai=" + getChunhiemdetai() +
            ", noidungtongquan='" + getNoidungtongquan() + "'" +
            ", kinhphiHoanthanh=" + getKinhphiHoanthanh() +
            ", tongdiem=" + getTongdiem() +
            ", ghichu='" + getGhichu() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            ", ngayCn='" + getNgayCn() + "'" +
            "}";
    }
}
