package com.vnpt.cdt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CdtNoiDen.
 */
@Entity
@Table(name = "cdt_noi_den")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtNoiDen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "csytid")
    private Long csytid;

    @ManyToOne
    @JsonIgnoreProperties("noidens")
    private CdtNoiDenCongTac cdtNoiDenCongTac;

    @ManyToOne
    @JsonIgnoreProperties("noidens")
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

    public CdtNoiDen csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public CdtNoiDenCongTac getCdtNoiDenCongTac() {
        return cdtNoiDenCongTac;
    }

    public CdtNoiDen cdtNoiDenCongTac(CdtNoiDenCongTac cdtNoiDenCongTac) {
        this.cdtNoiDenCongTac = cdtNoiDenCongTac;
        return this;
    }

    public void setCdtNoiDenCongTac(CdtNoiDenCongTac cdtNoiDenCongTac) {
        this.cdtNoiDenCongTac = cdtNoiDenCongTac;
    }

    public CdtChiDaoTuyen getCdtChiDaoTuyen() {
        return cdtChiDaoTuyen;
    }

    public CdtNoiDen cdtChiDaoTuyen(CdtChiDaoTuyen cdtChiDaoTuyen) {
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
        if (!(o instanceof CdtNoiDen)) {
            return false;
        }
        return id != null && id.equals(((CdtNoiDen) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtNoiDen{" +
            "id=" + getId() +
            ", csytid=" + getCsytid() +
            "}";
    }
}
