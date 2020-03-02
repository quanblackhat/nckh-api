package com.vnptit.vnpthis.domain.nckh;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DanhGia.
 */
@Entity
@Table(name = "NCKH_DANHGIA")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DanhGia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "danhgiaid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "diemdanhgia", nullable = false)
    private Integer diemdanhgia;

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
    @JsonIgnoreProperties("danhGias")
    private DeTai deTai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiemdanhgia() {
        return diemdanhgia;
    }

    public DanhGia diemdanhgia(Integer diemdanhgia) {
        this.diemdanhgia = diemdanhgia;
        return this;
    }

    public void setDiemdanhgia(Integer diemdanhgia) {
        this.diemdanhgia = diemdanhgia;
    }

    public String getGhichu() {
        return ghichu;
    }

    public DanhGia ghichu(String ghichu) {
        this.ghichu = ghichu;
        return this;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public LocalDate getNgayCn() {
        return ngayCn;
    }

    public DanhGia ngayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
        return this;
    }

    public void setNgayCn(LocalDate ngayCn) {
        this.ngayCn = ngayCn;
    }

    public Integer getNguoiCn() {
        return nguoiCn;
    }

    public DanhGia nguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
        return this;
    }

    public void setNguoiCn(Integer nguoiCn) {
        this.nguoiCn = nguoiCn;
    }

    public DeTai getDeTai() {
        return deTai;
    }

    public DanhGia deTai(DeTai deTai) {
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
        if (!(o instanceof DanhGia)) {
            return false;
        }
        return id != null && id.equals(((DanhGia) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DanhGia{" +
            "id=" + getId() +
            ", diemdanhgia=" + getDiemdanhgia() +
            ", ghichu='" + getGhichu() + "'" +
            ", ngayCn='" + getNgayCn() + "'" +
            ", nguoiCn=" + getNguoiCn() +
            "}";
    }
}
