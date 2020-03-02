package com.vnptit.vnpthis.domain.nckh;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DuToan.
 */
@Entity
@Table(name = "NCKH_DUTOAN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DuToan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dutoanid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "tien_dukien", nullable = false)
    private Integer tienDukien;

    @NotNull
    @Column(name = "tien_hoanthanh", nullable = false)
    private Integer tienHoanthanh;

    @NotNull
    @Column(name = "ghichu", nullable = false)
    private String ghichu;

    @NotNull
    @Column(name = "ngay_cn", nullable = false)
    private LocalDate ngayCn;

    @NotNull
    @Column(name = "nguoi_cn", nullable = false)
    private Integer nguoiCn;

    @ManyToOne
    @JoinColumn(name = "detaiid", insertable = false, updatable = false)
    @JsonIgnoreProperties("duToans")
    private DeTai deTai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTienDukien() {
        return tienDukien;
    }

    public DuToan tienDukien(Integer tienDukien) {
        this.tienDukien = tienDukien;
        return this;
    }

    public void setTienDukien(Integer tienDukien) {
        this.tienDukien = tienDukien;
    }

    public Integer getTienHoanthanh() {
        return tienHoanthanh;
    }

    public DuToan tienHoanthanh(Integer tienHoanthanh) {
        this.tienHoanthanh = tienHoanthanh;
        return this;
    }

    public void setTienHoanthanh(Integer tienHoanthanh) {
        this.tienHoanthanh = tienHoanthanh;
    }

    public String getGhichu() {
        return ghichu;
    }

    public DuToan ghichu(String ghichu) {
        this.ghichu = ghichu;
        return this;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public LocalDate getNgayCn() {
        return ngayCn;
    }

    public DuToan ngayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
        return this;
    }

    public void setNgayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
    }

    public Integer getNguoiCn() {
        return nguoiCn;
    }

    public DuToan nguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
        return this;
    }

    public void setNguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public DuToan deTai(DeTai deTai) {
        this.deTai = deTai;
        return this;
    }

    public void setDeTai(DeTai deTai) {
        this.deTai = deTai;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DuToan)) {
            return false;
        }
        return id != null && id.equals(((DuToan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DuToan{" +
            "id=" + getId() +
            ", tienDukien=" + getTienDukien() +
            ", tienHoanthanh=" + getTienHoanthanh() +
            ", ghichu='" + getGhichu() + "'" +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            "}";
    }
}
