package com.vnptit.vnpthis.domain.nckh;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DanhMuc.
 */
@Entity
@Table(name = "NCKH_DANHMUC")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DanhMuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "danhmucid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ma", nullable = false)
    private String ma;

    @NotNull
    @Column(name = "ngaytao", nullable = false)
    private LocalDate ngaytao;

    @NotNull
    @Column(name = "sudung", nullable = false)
    private Integer sudung;

    @NotNull
    @Column(name = "ten", nullable = false)
    private String ten;

    @NotNull
    @Column(name = "thutu", nullable = false)
    private Integer thutu;

    @ManyToOne
    @JsonIgnoreProperties("danhMucs")
    @JoinColumn(name = "loaiid", insertable = false, updatable = false)
    private LoaiDanhMuc loaiDanhMuc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public DanhMuc ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public LocalDate getNgaytao() {
        return ngaytao;
    }

    public DanhMuc ngaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
        return this;
    }

    public void setNgaytao(LocalDate ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Integer getSudung() {
        return sudung;
    }

    public DanhMuc sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    public String getTen() {
        return ten;
    }

    public DanhMuc ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getThutu() {
        return thutu;
    }

    public DanhMuc thutu(Integer thutu) {
        this.thutu = thutu;
        return this;
    }

    public void setThutu(Integer thutu) {
        this.thutu = thutu;
    }

    public LoaiDanhMuc getLoaiDanhMuc() {
        return loaiDanhMuc;
    }

    public DanhMuc loaiDanhMuc(LoaiDanhMuc loaiDanhMuc) {
        this.loaiDanhMuc = loaiDanhMuc;
        return this;
    }

    public void setLoaiDanhMuc(LoaiDanhMuc loaiDanhMuc) {
        this.loaiDanhMuc = loaiDanhMuc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMuc)) {
            return false;
        }
        return id != null && id.equals(((DanhMuc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DanhMuc{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ngaytao='" + getNgaytao() + "'" +
            ", sudung=" + getSudung() +
            ", ten='" + getTen() + "'" +
            ", thutu=" + getThutu() +
            "}";
    }
}
