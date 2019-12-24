package com.vnpt.cdt.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CdtNoiDenCongTac.
 */
@Entity
@Table(name = "ndct")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtNoiDenCongTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 50)
    @Column(name = "ma_noi_den", length = 50)
    private String maNoiDen;

    @Size(max = 1000)
    @Column(name = "ten_noi_den", length = 1000)
    private String tenNoiDen;

    @Column(name = "thu_tu_sap_xep")
    private Long thuTuSapXep;

    @Column(name = "csytid")
    private Long csytid;

    @OneToMany(mappedBy = "cdtNoiDenCongTac")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtNoiDen> noidens = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNoiDen() {
        return maNoiDen;
    }

    public CdtNoiDenCongTac maNoiDen(String maNoiDen) {
        this.maNoiDen = maNoiDen;
        return this;
    }

    public void setMaNoiDen(String maNoiDen) {
        this.maNoiDen = maNoiDen;
    }

    public String getTenNoiDen() {
        return tenNoiDen;
    }

    public CdtNoiDenCongTac tenNoiDen(String tenNoiDen) {
        this.tenNoiDen = tenNoiDen;
        return this;
    }

    public void setTenNoiDen(String tenNoiDen) {
        this.tenNoiDen = tenNoiDen;
    }

    public Long getThuTuSapXep() {
        return thuTuSapXep;
    }

    public CdtNoiDenCongTac thuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
        return this;
    }

    public void setThuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
    }

    public Long getCsytid() {
        return csytid;
    }

    public CdtNoiDenCongTac csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public Set<CdtNoiDen> getNoidens() {
        return noidens;
    }

    public CdtNoiDenCongTac noidens(Set<CdtNoiDen> cdtNoiDens) {
        this.noidens = cdtNoiDens;
        return this;
    }

    public CdtNoiDenCongTac addNoiden(CdtNoiDen cdtNoiDen) {
        this.noidens.add(cdtNoiDen);
        cdtNoiDen.setCdtNoiDenCongTac(this);
        return this;
    }

    public CdtNoiDenCongTac removeNoiden(CdtNoiDen cdtNoiDen) {
        this.noidens.remove(cdtNoiDen);
        cdtNoiDen.setCdtNoiDenCongTac(null);
        return this;
    }

    public void setNoidens(Set<CdtNoiDen> cdtNoiDens) {
        this.noidens = cdtNoiDens;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CdtNoiDenCongTac)) {
            return false;
        }
        return id != null && id.equals(((CdtNoiDenCongTac) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtNoiDenCongTac{" +
            "id=" + getId() +
            ", maNoiDen='" + getMaNoiDen() + "'" +
            ", tenNoiDen='" + getTenNoiDen() + "'" +
            ", thuTuSapXep=" + getThuTuSapXep() +
            ", csytid=" + getCsytid() +
            "}";
    }
}
