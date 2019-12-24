package com.vnpt.cdt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CdtKyThuat.
 */
@Entity
@Table(name = "cdt_ky_thuat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtKyThuat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "csytid")
    private Long csytid;

    @ManyToOne
    @JsonIgnoreProperties("kythuats")
    private CdtKyThuatHoTro cdtKyThuatHoTro;

    @ManyToOne
    @JsonIgnoreProperties("kythuats")
    private CdtChiDaoTuyen cdtChiDaoTuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCsytid() {
        return csytid;
    }

    public CdtKyThuat csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public CdtKyThuatHoTro getCdtKyThuatHoTro() {
        return cdtKyThuatHoTro;
    }

    public CdtKyThuat cdtKyThuatHoTro(CdtKyThuatHoTro cdtKyThuatHoTro) {
        this.cdtKyThuatHoTro = cdtKyThuatHoTro;
        return this;
    }

    public void setCdtKyThuatHoTro(CdtKyThuatHoTro cdtKyThuatHoTro) {
        this.cdtKyThuatHoTro = cdtKyThuatHoTro;
    }

    public CdtChiDaoTuyen getCdtChiDaoTuyen() {
        return cdtChiDaoTuyen;
    }

    public CdtKyThuat cdtChiDaoTuyen(CdtChiDaoTuyen cdtChiDaoTuyen) {
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
        if (!(o instanceof CdtKyThuat)) {
            return false;
        }
        return id != null && id.equals(((CdtKyThuat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtKyThuat{" +
            "id=" + getId() +
            ", csytid=" + getCsytid() +
            "}";
    }
}
