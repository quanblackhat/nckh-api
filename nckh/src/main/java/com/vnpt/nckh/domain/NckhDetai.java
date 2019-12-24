package com.vnpt.nckh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * NckhDetai entity.\n@author VINHHC.
 */
@ApiModel(description = "NckhDetai entity.\n@author VINHHC.")
@Entity
@Table(name = "nckh_detai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NckhDetai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "ma_detai", length = 30, nullable = false)
    private String maDetai;

    @NotNull
    @Size(max = 255)
    @Column(name = "ten_detai", length = 255, nullable = false)
    private String tenDetai;

    @Size(max = 4000)
    @Column(name = "muctieu", length = 4000)
    private String muctieu;

    @Column(name = "ngay_bd_du_kien")
    private Instant ngayBdDuKien;

    @Column(name = "ngay_kt_du_kien")
    private Instant ngayKtDuKien;

    @Column(name = "kinh_phi_du_kien")
    private Float kinhPhiDuKien;

    @Size(max = 4000)
    @Column(name = "noi_dung_tong_quan", length = 4000)
    private String noiDungTongQuan;

    @Column(name = "kinh_phi_hoan_thanh")
    private Float kinhPhiHoanThanh;

    @Column(name = "tong_diem")
    private Float tongDiem;

    @OneToMany(mappedBy = "nckhDetai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NckhNhanSu> nckhNhanSus = new HashSet<>();

    @OneToMany(mappedBy = "nckhDetai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NckhTiendo> nckhTiendos = new HashSet<>();

    @OneToMany(mappedBy = "nckhDetai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NckhUpfile> nckhUpfiles = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("nckhDetais")
    private NckhDanhmuc nckhDanhmuc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDetai() {
        return maDetai;
    }

    public NckhDetai maDetai(String maDetai) {
        this.maDetai = maDetai;
        return this;
    }

    public void setMaDetai(String maDetai) {
        this.maDetai = maDetai;
    }

    public String getTenDetai() {
        return tenDetai;
    }

    public NckhDetai tenDetai(String tenDetai) {
        this.tenDetai = tenDetai;
        return this;
    }

    public void setTenDetai(String tenDetai) {
        this.tenDetai = tenDetai;
    }

    public String getMuctieu() {
        return muctieu;
    }

    public NckhDetai muctieu(String muctieu) {
        this.muctieu = muctieu;
        return this;
    }

    public void setMuctieu(String muctieu) {
        this.muctieu = muctieu;
    }

    public Instant getNgayBdDuKien() {
        return ngayBdDuKien;
    }

    public NckhDetai ngayBdDuKien(Instant ngayBdDuKien) {
        this.ngayBdDuKien = ngayBdDuKien;
        return this;
    }

    public void setNgayBdDuKien(Instant ngayBdDuKien) {
        this.ngayBdDuKien = ngayBdDuKien;
    }

    public Instant getNgayKtDuKien() {
        return ngayKtDuKien;
    }

    public NckhDetai ngayKtDuKien(Instant ngayKtDuKien) {
        this.ngayKtDuKien = ngayKtDuKien;
        return this;
    }

    public void setNgayKtDuKien(Instant ngayKtDuKien) {
        this.ngayKtDuKien = ngayKtDuKien;
    }

    public Float getKinhPhiDuKien() {
        return kinhPhiDuKien;
    }

    public NckhDetai kinhPhiDuKien(Float kinhPhiDuKien) {
        this.kinhPhiDuKien = kinhPhiDuKien;
        return this;
    }

    public void setKinhPhiDuKien(Float kinhPhiDuKien) {
        this.kinhPhiDuKien = kinhPhiDuKien;
    }

    public String getNoiDungTongQuan() {
        return noiDungTongQuan;
    }

    public NckhDetai noiDungTongQuan(String noiDungTongQuan) {
        this.noiDungTongQuan = noiDungTongQuan;
        return this;
    }

    public void setNoiDungTongQuan(String noiDungTongQuan) {
        this.noiDungTongQuan = noiDungTongQuan;
    }

    public Float getKinhPhiHoanThanh() {
        return kinhPhiHoanThanh;
    }

    public NckhDetai kinhPhiHoanThanh(Float kinhPhiHoanThanh) {
        this.kinhPhiHoanThanh = kinhPhiHoanThanh;
        return this;
    }

    public void setKinhPhiHoanThanh(Float kinhPhiHoanThanh) {
        this.kinhPhiHoanThanh = kinhPhiHoanThanh;
    }

    public Float getTongDiem() {
        return tongDiem;
    }

    public NckhDetai tongDiem(Float tongDiem) {
        this.tongDiem = tongDiem;
        return this;
    }

    public void setTongDiem(Float tongDiem) {
        this.tongDiem = tongDiem;
    }

    public Set<NckhNhanSu> getNckhNhanSus() {
        return nckhNhanSus;
    }

    public NckhDetai nckhNhanSus(Set<NckhNhanSu> nckhNhanSus) {
        this.nckhNhanSus = nckhNhanSus;
        return this;
    }

    public NckhDetai addNckhNhanSu(NckhNhanSu nckhNhanSu) {
        this.nckhNhanSus.add(nckhNhanSu);
        nckhNhanSu.setNckhDetai(this);
        return this;
    }

    public NckhDetai removeNckhNhanSu(NckhNhanSu nckhNhanSu) {
        this.nckhNhanSus.remove(nckhNhanSu);
        nckhNhanSu.setNckhDetai(null);
        return this;
    }

    public void setNckhNhanSus(Set<NckhNhanSu> nckhNhanSus) {
        this.nckhNhanSus = nckhNhanSus;
    }

    public Set<NckhTiendo> getNckhTiendos() {
        return nckhTiendos;
    }

    public NckhDetai nckhTiendos(Set<NckhTiendo> nckhTiendos) {
        this.nckhTiendos = nckhTiendos;
        return this;
    }

    public NckhDetai addNckhTiendo(NckhTiendo nckhTiendo) {
        this.nckhTiendos.add(nckhTiendo);
        nckhTiendo.setNckhDetai(this);
        return this;
    }

    public NckhDetai removeNckhTiendo(NckhTiendo nckhTiendo) {
        this.nckhTiendos.remove(nckhTiendo);
        nckhTiendo.setNckhDetai(null);
        return this;
    }

    public void setNckhTiendos(Set<NckhTiendo> nckhTiendos) {
        this.nckhTiendos = nckhTiendos;
    }

    public Set<NckhUpfile> getNckhUpfiles() {
        return nckhUpfiles;
    }

    public NckhDetai nckhUpfiles(Set<NckhUpfile> nckhUpfiles) {
        this.nckhUpfiles = nckhUpfiles;
        return this;
    }

    public NckhDetai addNckhUpfile(NckhUpfile nckhUpfile) {
        this.nckhUpfiles.add(nckhUpfile);
        nckhUpfile.setNckhDetai(this);
        return this;
    }

    public NckhDetai removeNckhUpfile(NckhUpfile nckhUpfile) {
        this.nckhUpfiles.remove(nckhUpfile);
        nckhUpfile.setNckhDetai(null);
        return this;
    }

    public void setNckhUpfiles(Set<NckhUpfile> nckhUpfiles) {
        this.nckhUpfiles = nckhUpfiles;
    }

    public NckhDanhmuc getNckhDanhmuc() {
        return nckhDanhmuc;
    }

    public NckhDetai nckhDanhmuc(NckhDanhmuc nckhDanhmuc) {
        this.nckhDanhmuc = nckhDanhmuc;
        return this;
    }

    public void setNckhDanhmuc(NckhDanhmuc nckhDanhmuc) {
        this.nckhDanhmuc = nckhDanhmuc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NckhDetai)) {
            return false;
        }
        return id != null && id.equals(((NckhDetai) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NckhDetai{" +
            "id=" + getId() +
            ", maDetai='" + getMaDetai() + "'" +
            ", tenDetai='" + getTenDetai() + "'" +
            ", muctieu='" + getMuctieu() + "'" +
            ", ngayBdDuKien='" + getNgayBdDuKien() + "'" +
            ", ngayKtDuKien='" + getNgayKtDuKien() + "'" +
            ", kinhPhiDuKien=" + getKinhPhiDuKien() +
            ", noiDungTongQuan='" + getNoiDungTongQuan() + "'" +
            ", kinhPhiHoanThanh=" + getKinhPhiHoanThanh() +
            ", tongDiem=" + getTongDiem() +
            "}";
    }
}
