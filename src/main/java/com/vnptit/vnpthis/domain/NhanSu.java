package com.vnptit.vnpthis.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A NhanSu.
 */
@Entity
@Table(name = "NCKH_NHANSU")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NhanSu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "nhansuid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "chunhiemdetai", nullable = false)
    private Integer chunhiemdetai;

    @NotNull
    @Column(name = "ngay_cn", nullable = false)
    private LocalDate ngayCn;

    @NotNull
    @Column(name = "nguoi_cn", nullable = false)
    private Integer nguoiCn;

    @ManyToOne
    @JoinColumn(name = "detaiid", insertable = false, updatable = false)
    @JsonIgnoreProperties("nhanSus")
    private DeTai deTai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChunhiemdetai() {
        return chunhiemdetai;
    }

    public NhanSu chunhiemdetai(Integer chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
        return this;
    }

    public void setChunhiemdetai(Integer chunhiemdetai) {
        this.chunhiemdetai = chunhiemdetai;
    }

    public LocalDate getNgayCn() {
        return ngayCn;
    }

    public NhanSu ngayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
        return this;
    }

    public void setNgayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
    }

    public Integer getNguoiCn() {
        return nguoiCn;
    }

    public NhanSu nguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
        return this;
    }

    public void setNguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public NhanSu deTai(DeTai deTai) {
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
        if (!(o instanceof NhanSu)) {
            return false;
        }
        return id != null && id.equals(((NhanSu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NhanSu{" +
            "id=" + getId() +
            ", chunhiemdetai=" + getChunhiemdetai() +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            "}";
    }
}
