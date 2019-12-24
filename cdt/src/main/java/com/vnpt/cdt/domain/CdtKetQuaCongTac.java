package com.vnpt.cdt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A CdtKetQuaCongTac.
 */
@Entity
@Table(name = "kqct")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtKetQuaCongTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 50)
    @Column(name = "ma_ket_qua", length = 50)
    private String maKetQua;

    @Size(max = 1000)
    @Column(name = "ten_ket_qua", length = 1000)
    private String tenKetQua;

    @Column(name = "thu_tu_sap_xep")
    private Long thuTuSapXep;

    @Column(name = "csytid")
    private Long csytid;

    @ManyToOne
    @JsonIgnoreProperties("ketquacongtacs")
    private CdtChiDaoTuyen cdtChiDaoTuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKetQua() {
        return maKetQua;
    }

    public CdtKetQuaCongTac maKetQua(String maKetQua) {
        this.maKetQua = maKetQua;
        return this;
    }

    public void setMaKetQua(String maKetQua) {
        this.maKetQua = maKetQua;
    }

    public String getTenKetQua() {
        return tenKetQua;
    }

    public CdtKetQuaCongTac tenKetQua(String tenKetQua) {
        this.tenKetQua = tenKetQua;
        return this;
    }

    public void setTenKetQua(String tenKetQua) {
        this.tenKetQua = tenKetQua;
    }

    public Long getThuTuSapXep() {
        return thuTuSapXep;
    }

    public CdtKetQuaCongTac thuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
        return this;
    }

    public void setThuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
    }

    public Long getCsytid() {
        return csytid;
    }

    public CdtKetQuaCongTac csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public CdtChiDaoTuyen getCdtChiDaoTuyen() {
        return cdtChiDaoTuyen;
    }

    public CdtKetQuaCongTac cdtChiDaoTuyen(CdtChiDaoTuyen cdtChiDaoTuyen) {
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
        if (!(o instanceof CdtKetQuaCongTac)) {
            return false;
        }
        return id != null && id.equals(((CdtKetQuaCongTac) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtKetQuaCongTac{" +
            "id=" + getId() +
            ", maKetQua='" + getMaKetQua() + "'" +
            ", tenKetQua='" + getTenKetQua() + "'" +
            ", thuTuSapXep=" + getThuTuSapXep() +
            ", csytid=" + getCsytid() +
            "}";
    }
}
