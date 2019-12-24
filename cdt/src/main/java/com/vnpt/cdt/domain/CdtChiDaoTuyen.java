package com.vnpt.cdt.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A CdtChiDaoTuyen.
 */
@Entity
@Table(name = "cdt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtChiDaoTuyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "so_quyet_dinh")
    private String soQuyetDinh;

    @Column(name = "ngay_quyet_dinh")
    private Instant ngayQuyetDinh;

    @Column(name = "so_hop_dong")
    private String soHopDong;

    @Column(name = "ngay_hop_dong")
    private Instant ngayHopDong;

    @Size(max = 4000)
    @Column(name = "ghi_chu", length = 4000)
    private String ghiChu;

    @Size(max = 4000)
    @Column(name = "noi_dung", length = 4000)
    private String noiDung;

    @Column(name = "ngay_bd")
    private Instant ngayBD;

    @Column(name = "ngay_kt")
    private Instant ngayKT;

    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @Column(name = "so_benh_nhan_kham")
    private Long soBenhNhanKham;

    @Column(name = "so_benh_nhan_ky_thuat")
    private Long soBenhNhanKyThuat;

    @Column(name = "so_can_bo_chuyen_giao")
    private Long soCanBoChuyenGiao;

    @OneToMany(mappedBy = "cdtChiDaoTuyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtNhanVien> nhanviens = new HashSet<>();

    @OneToMany(mappedBy = "cdtChiDaoTuyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtNoiDen> noidens = new HashSet<>();

    @OneToMany(mappedBy = "cdtChiDaoTuyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtKyThuat> kythuats = new HashSet<>();

    @OneToMany(mappedBy = "cdtChiDaoTuyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtVatTu> vattus = new HashSet<>();

    @OneToMany(mappedBy = "cdtChiDaoTuyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtLyDoCongTac> lydocongtacs = new HashSet<>();

    @OneToMany(mappedBy = "cdtChiDaoTuyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtKetQuaCongTac> ketquacongtacs = new HashSet<>();

    @OneToMany(mappedBy = "cdtChiDaoTuyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtTaiChinh> taichinhs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoQuyetDinh() {
        return soQuyetDinh;
    }

    public CdtChiDaoTuyen soQuyetDinh(String soQuyetDinh) {
        this.soQuyetDinh = soQuyetDinh;
        return this;
    }

    public void setSoQuyetDinh(String soQuyetDinh) {
        this.soQuyetDinh = soQuyetDinh;
    }

    public Instant getNgayQuyetDinh() {
        return ngayQuyetDinh;
    }

    public CdtChiDaoTuyen ngayQuyetDinh(Instant ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
        return this;
    }

    public void setNgayQuyetDinh(Instant ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
    }

    public String getSoHopDong() {
        return soHopDong;
    }

    public CdtChiDaoTuyen soHopDong(String soHopDong) {
        this.soHopDong = soHopDong;
        return this;
    }

    public void setSoHopDong(String soHopDong) {
        this.soHopDong = soHopDong;
    }

    public Instant getNgayHopDong() {
        return ngayHopDong;
    }

    public CdtChiDaoTuyen ngayHopDong(Instant ngayHopDong) {
        this.ngayHopDong = ngayHopDong;
        return this;
    }

    public void setNgayHopDong(Instant ngayHopDong) {
        this.ngayHopDong = ngayHopDong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public CdtChiDaoTuyen ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public CdtChiDaoTuyen noiDung(String noiDung) {
        this.noiDung = noiDung;
        return this;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Instant getNgayBD() {
        return ngayBD;
    }

    public CdtChiDaoTuyen ngayBD(Instant ngayBD) {
        this.ngayBD = ngayBD;
        return this;
    }

    public void setNgayBD(Instant ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Instant getNgayKT() {
        return ngayKT;
    }

    public CdtChiDaoTuyen ngayKT(Instant ngayKT) {
        this.ngayKT = ngayKT;
        return this;
    }

    public void setNgayKT(Instant ngayKT) {
        this.ngayKT = ngayKT;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public CdtChiDaoTuyen ngayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
        return this;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Long getSoBenhNhanKham() {
        return soBenhNhanKham;
    }

    public CdtChiDaoTuyen soBenhNhanKham(Long soBenhNhanKham) {
        this.soBenhNhanKham = soBenhNhanKham;
        return this;
    }

    public void setSoBenhNhanKham(Long soBenhNhanKham) {
        this.soBenhNhanKham = soBenhNhanKham;
    }

    public Long getSoBenhNhanKyThuat() {
        return soBenhNhanKyThuat;
    }

    public CdtChiDaoTuyen soBenhNhanKyThuat(Long soBenhNhanKyThuat) {
        this.soBenhNhanKyThuat = soBenhNhanKyThuat;
        return this;
    }

    public void setSoBenhNhanKyThuat(Long soBenhNhanKyThuat) {
        this.soBenhNhanKyThuat = soBenhNhanKyThuat;
    }

    public Long getSoCanBoChuyenGiao() {
        return soCanBoChuyenGiao;
    }

    public CdtChiDaoTuyen soCanBoChuyenGiao(Long soCanBoChuyenGiao) {
        this.soCanBoChuyenGiao = soCanBoChuyenGiao;
        return this;
    }

    public void setSoCanBoChuyenGiao(Long soCanBoChuyenGiao) {
        this.soCanBoChuyenGiao = soCanBoChuyenGiao;
    }

    public Set<CdtNhanVien> getNhanviens() {
        return nhanviens;
    }

    public CdtChiDaoTuyen nhanviens(Set<CdtNhanVien> cdtNhanViens) {
        this.nhanviens = cdtNhanViens;
        return this;
    }

    public CdtChiDaoTuyen addNhanvien(CdtNhanVien cdtNhanVien) {
        this.nhanviens.add(cdtNhanVien);
        cdtNhanVien.setCdtChiDaoTuyen(this);
        return this;
    }

    public CdtChiDaoTuyen removeNhanvien(CdtNhanVien cdtNhanVien) {
        this.nhanviens.remove(cdtNhanVien);
        cdtNhanVien.setCdtChiDaoTuyen(null);
        return this;
    }

    public void setNhanviens(Set<CdtNhanVien> cdtNhanViens) {
        this.nhanviens = cdtNhanViens;
    }

    public Set<CdtNoiDen> getNoidens() {
        return noidens;
    }

    public CdtChiDaoTuyen noidens(Set<CdtNoiDen> cdtNoiDens) {
        this.noidens = cdtNoiDens;
        return this;
    }

    public CdtChiDaoTuyen addNoiden(CdtNoiDen cdtNoiDen) {
        this.noidens.add(cdtNoiDen);
        cdtNoiDen.setCdtChiDaoTuyen(this);
        return this;
    }

    public CdtChiDaoTuyen removeNoiden(CdtNoiDen cdtNoiDen) {
        this.noidens.remove(cdtNoiDen);
        cdtNoiDen.setCdtChiDaoTuyen(null);
        return this;
    }

    public void setNoidens(Set<CdtNoiDen> cdtNoiDens) {
        this.noidens = cdtNoiDens;
    }

    public Set<CdtKyThuat> getKythuats() {
        return kythuats;
    }

    public CdtChiDaoTuyen kythuats(Set<CdtKyThuat> cdtKyThuats) {
        this.kythuats = cdtKyThuats;
        return this;
    }

    public CdtChiDaoTuyen addKythuat(CdtKyThuat cdtKyThuat) {
        this.kythuats.add(cdtKyThuat);
        cdtKyThuat.setCdtChiDaoTuyen(this);
        return this;
    }

    public CdtChiDaoTuyen removeKythuat(CdtKyThuat cdtKyThuat) {
        this.kythuats.remove(cdtKyThuat);
        cdtKyThuat.setCdtChiDaoTuyen(null);
        return this;
    }

    public void setKythuats(Set<CdtKyThuat> cdtKyThuats) {
        this.kythuats = cdtKyThuats;
    }

    public Set<CdtVatTu> getVattus() {
        return vattus;
    }

    public CdtChiDaoTuyen vattus(Set<CdtVatTu> cdtVatTus) {
        this.vattus = cdtVatTus;
        return this;
    }

    public CdtChiDaoTuyen addVattu(CdtVatTu cdtVatTu) {
        this.vattus.add(cdtVatTu);
        cdtVatTu.setCdtChiDaoTuyen(this);
        return this;
    }

    public CdtChiDaoTuyen removeVattu(CdtVatTu cdtVatTu) {
        this.vattus.remove(cdtVatTu);
        cdtVatTu.setCdtChiDaoTuyen(null);
        return this;
    }

    public void setVattus(Set<CdtVatTu> cdtVatTus) {
        this.vattus = cdtVatTus;
    }

    public Set<CdtLyDoCongTac> getLydocongtacs() {
        return lydocongtacs;
    }

    public CdtChiDaoTuyen lydocongtacs(Set<CdtLyDoCongTac> cdtLyDoCongTacs) {
        this.lydocongtacs = cdtLyDoCongTacs;
        return this;
    }

    public CdtChiDaoTuyen addLydocongtac(CdtLyDoCongTac cdtLyDoCongTac) {
        this.lydocongtacs.add(cdtLyDoCongTac);
        cdtLyDoCongTac.setCdtChiDaoTuyen(this);
        return this;
    }

    public CdtChiDaoTuyen removeLydocongtac(CdtLyDoCongTac cdtLyDoCongTac) {
        this.lydocongtacs.remove(cdtLyDoCongTac);
        cdtLyDoCongTac.setCdtChiDaoTuyen(null);
        return this;
    }

    public void setLydocongtacs(Set<CdtLyDoCongTac> cdtLyDoCongTacs) {
        this.lydocongtacs = cdtLyDoCongTacs;
    }

    public Set<CdtKetQuaCongTac> getKetquacongtacs() {
        return ketquacongtacs;
    }

    public CdtChiDaoTuyen ketquacongtacs(Set<CdtKetQuaCongTac> cdtKetQuaCongTacs) {
        this.ketquacongtacs = cdtKetQuaCongTacs;
        return this;
    }

    public CdtChiDaoTuyen addKetquacongtac(CdtKetQuaCongTac cdtKetQuaCongTac) {
        this.ketquacongtacs.add(cdtKetQuaCongTac);
        cdtKetQuaCongTac.setCdtChiDaoTuyen(this);
        return this;
    }

    public CdtChiDaoTuyen removeKetquacongtac(CdtKetQuaCongTac cdtKetQuaCongTac) {
        this.ketquacongtacs.remove(cdtKetQuaCongTac);
        cdtKetQuaCongTac.setCdtChiDaoTuyen(null);
        return this;
    }

    public void setKetquacongtacs(Set<CdtKetQuaCongTac> cdtKetQuaCongTacs) {
        this.ketquacongtacs = cdtKetQuaCongTacs;
    }

    public Set<CdtTaiChinh> getTaichinhs() {
        return taichinhs;
    }

    public CdtChiDaoTuyen taichinhs(Set<CdtTaiChinh> cdtTaiChinhs) {
        this.taichinhs = cdtTaiChinhs;
        return this;
    }

    public CdtChiDaoTuyen addTaichinh(CdtTaiChinh cdtTaiChinh) {
        this.taichinhs.add(cdtTaiChinh);
        cdtTaiChinh.setCdtChiDaoTuyen(this);
        return this;
    }

    public CdtChiDaoTuyen removeTaichinh(CdtTaiChinh cdtTaiChinh) {
        this.taichinhs.remove(cdtTaiChinh);
        cdtTaiChinh.setCdtChiDaoTuyen(null);
        return this;
    }

    public void setTaichinhs(Set<CdtTaiChinh> cdtTaiChinhs) {
        this.taichinhs = cdtTaiChinhs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CdtChiDaoTuyen)) {
            return false;
        }
        return id != null && id.equals(((CdtChiDaoTuyen) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtChiDaoTuyen{" +
            "id=" + getId() +
            ", soQuyetDinh='" + getSoQuyetDinh() + "'" +
            ", ngayQuyetDinh='" + getNgayQuyetDinh() + "'" +
            ", soHopDong='" + getSoHopDong() + "'" +
            ", ngayHopDong='" + getNgayHopDong() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", ngayBD='" + getNgayBD() + "'" +
            ", ngayKT='" + getNgayKT() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", soBenhNhanKham=" + getSoBenhNhanKham() +
            ", soBenhNhanKyThuat=" + getSoBenhNhanKyThuat() +
            ", soCanBoChuyenGiao=" + getSoCanBoChuyenGiao() +
            "}";
    }
}
