package com.vnpt.cdt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CdtTaiChinh.
 */
@Entity
@Table(name = "cdt_tai_chinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtTaiChinh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "loai")
    private String loai;

    @Column(name = "so_tien")
    private Long soTien;

    @Column(name = "csytid")
    private Long csytid;

    @ManyToOne
    @JsonIgnoreProperties("taichinhs")
    private CdtChiDaoTuyen cdtChiDaoTuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoai() {
        return loai;
    }

    public CdtTaiChinh loai(String loai) {
        this.loai = loai;
        return this;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Long getSoTien() {
        return soTien;
    }

    public CdtTaiChinh soTien(Long soTien) {
        this.soTien = soTien;
        return this;
    }

    public void setSoTien(Long soTien) {
        this.soTien = soTien;
    }

    public Long getCsytid() {
        return csytid;
    }

    public CdtTaiChinh csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public CdtChiDaoTuyen getCdtChiDaoTuyen() {
        return cdtChiDaoTuyen;
    }

    public CdtTaiChinh cdtChiDaoTuyen(CdtChiDaoTuyen cdtChiDaoTuyen) {
        this.cdtChiDaoTuyen = cdtChiDaoTuyen;
        return this;
    }

    public void setCdtChiDaoTuyen(CdtChiDaoTuyen cdtChiDaoTuyen) {
        this.cdtChiDaoTuyen = cdtChiDaoTuyen;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CdtTaiChinh)) {
            return false;
        }
        return id != null && id.equals(((CdtTaiChinh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtTaiChinh{" +
            "id=" + getId() +
            ", loai='" + getLoai() + "'" +
            ", soTien=" + getSoTien() +
            ", csytid=" + getCsytid() +
            "}";
    }
}
