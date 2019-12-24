package com.vnpt.cdt.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CdtKyThuatHoTro.
 */
@Entity
@Table(name = "kthtro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtKyThuatHoTro implements Serializable {

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

    @OneToMany(mappedBy = "cdtKyThuatHoTro")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CdtKyThuat> kythuats = new HashSet<>();

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

    public CdtKyThuatHoTro maNoiDen(String maNoiDen) {
        this.maNoiDen = maNoiDen;
        return this;
    }

    public void setMaNoiDen(String maNoiDen) {
        this.maNoiDen = maNoiDen;
    }

    public String getTenNoiDen() {
        return tenNoiDen;
    }

    public CdtKyThuatHoTro tenNoiDen(String tenNoiDen) {
        this.tenNoiDen = tenNoiDen;
        return this;
    }

    public void setTenNoiDen(String tenNoiDen) {
        this.tenNoiDen = tenNoiDen;
    }

    public Long getThuTuSapXep() {
        return thuTuSapXep;
    }

    public CdtKyThuatHoTro thuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
        return this;
    }

    public void setThuTuSapXep(Long thuTuSapXep) {
        this.thuTuSapXep = thuTuSapXep;
    }

    public Long getCsytid() {
        return csytid;
    }

    public CdtKyThuatHoTro csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public Set<CdtKyThuat> getKythuats() {
        return kythuats;
    }

    public CdtKyThuatHoTro kythuats(Set<CdtKyThuat> cdtKyThuats) {
        this.kythuats = cdtKyThuats;
        return this;
    }

    public CdtKyThuatHoTro addKythuat(CdtKyThuat cdtKyThuat) {
        this.kythuats.add(cdtKyThuat);
        cdtKyThuat.setCdtKyThuatHoTro(this);
        return this;
    }

    public CdtKyThuatHoTro removeKythuat(CdtKyThuat cdtKyThuat) {
        this.kythuats.remove(cdtKyThuat);
        cdtKyThuat.setCdtKyThuatHoTro(null);
        return this;
    }

    public void setKythuats(Set<CdtKyThuat> cdtKyThuats) {
        this.kythuats = cdtKyThuats;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CdtKyThuatHoTro)) {
            return false;
        }
        return id != null && id.equals(((CdtKyThuatHoTro) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtKyThuatHoTro{" +
            "id=" + getId() +
            ", maNoiDen='" + getMaNoiDen() + "'" +
            ", tenNoiDen='" + getTenNoiDen() + "'" +
            ", thuTuSapXep=" + getThuTuSapXep() +
            ", csytid=" + getCsytid() +
            "}";
    }
}
