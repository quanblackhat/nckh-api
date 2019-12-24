package com.vnpt.cdt.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CdtVatTuHoTro.
 */
@Entity
@Table(name = "vtht")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtVatTuHoTro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 50)
    @Column(name = "ma_vat_tu", length = 50)
    private String maVatTu;

    @Size(max = 1000)
    @Column(name = "ten_vat_tu", length = 1000)
    private String tenVatTu;

    @Column(name = "thu_tu_sap_xep")
    private Long thuTuSapXep;

    @Column(name = "csytid")
    private Long csytid;

    @OneToMany(mappedBy = "cdtVatTuHoTro")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtVatTu> vattus = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaVatTu() {
        return maVatTu;
    }

    public CdtVatTuHoTro maVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
        return this;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public String getTenVatTu() {
        return tenVatTu;
    }

    public CdtVatTuHoTro tenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
        return this;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
    }

    public Long getThuTuSapXep() {
        return thuTuSapXep;
    }

    public CdtVatTuHoTro thuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
        return this;
    }

    public void setThuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
    }

    public Long getCsytid() {
        return csytid;
    }

    public CdtVatTuHoTro csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public Set<CdtVatTu> getVattus() {
        return vattus;
    }

    public CdtVatTuHoTro vattus(Set<CdtVatTu> cdtVatTus) {
        this.vattus = cdtVatTus;
        return this;
    }

    public CdtVatTuHoTro addVattu(CdtVatTu cdtVatTu) {
        this.vattus.add(cdtVatTu);
        cdtVatTu.setCdtVatTuHoTro(this);
        return this;
    }

    public CdtVatTuHoTro removeVattu(CdtVatTu cdtVatTu) {
        this.vattus.remove(cdtVatTu);
        cdtVatTu.setCdtVatTuHoTro(null);
        return this;
    }

    public void setVattus(Set<CdtVatTu> cdtVatTus) {
        this.vattus = cdtVatTus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CdtVatTuHoTro)) {
            return false;
        }
        return id != null && id.equals(((CdtVatTuHoTro) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtVatTuHoTro{" +
            "id=" + getId() +
            ", maVatTu='" + getMaVatTu() + "'" +
            ", tenVatTu='" + getTenVatTu() + "'" +
            ", thuTuSapXep=" + getThuTuSapXep() +
            ", csytid=" + getCsytid() +
            "}";
    }
}
