package com.vnpt.cdt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A CdtLyDoCongTac.
 */
@Entity
@Table(name = "ldct")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtLyDoCongTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 50)
    @Column(name = "ma_ly_do", length = 50)
    private String maLyDo;

    @Size(max = 1000)
    @Column(name = "ten_ly_do", length = 1000)
    private String tenLyDo;

    @Column(name = "thu_tu_sap_xep")
    private Long thuTuSapXep;

    @Column(name = "csytid")
    private Long csytid;

    @ManyToOne
    @JsonIgnoreProperties("lydocongtacs")
    private CdtChiDaoTuyen cdtChiDaoTuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLyDo() {
        return maLyDo;
    }

    public CdtLyDoCongTac maLyDo(String maLyDo) {
        this.maLyDo = maLyDo;
        return this;
    }

    public void setMaLyDo(String maLyDo) {
        this.maLyDo = maLyDo;
    }

    public String getTenLyDo() {
        return tenLyDo;
    }

    public CdtLyDoCongTac tenLyDo(String tenLyDo) {
        this.tenLyDo = tenLyDo;
        return this;
    }

    public void setTenLyDo(String tenLyDo) {
        this.tenLyDo = tenLyDo;
    }

    public Long getThuTuSapXep() {
        return thuTuSapXep;
    }

    public CdtLyDoCongTac thuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
        return this;
    }

    public void setThuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
    }

    public Long getCsytid() {
        return csytid;
    }

    public CdtLyDoCongTac csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public CdtChiDaoTuyen getCdtChiDaoTuyen() {
        return cdtChiDaoTuyen;
    }

    public CdtLyDoCongTac cdtChiDaoTuyen(CdtChiDaoTuyen cdtChiDaoTuyen) {
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
        if (!(o instanceof CdtLyDoCongTac)) {
            return false;
        }
        return id != null && id.equals(((CdtLyDoCongTac) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtLyDoCongTac{" +
            "id=" + getId() +
            ", maLyDo='" + getMaLyDo() + "'" +
            ", tenLyDo='" + getTenLyDo() + "'" +
            ", thuTuSapXep=" + getThuTuSapXep() +
            ", csytid=" + getCsytid() +
            "}";
    }
}
