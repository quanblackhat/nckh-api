package com.vnpt.cdt.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CdtVatTu.
 */
@Entity
@Table(name = "cdt_vat_tu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CdtVatTu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "csytid")
    private Long csytid;

    @ManyToOne
    @JsonIgnoreProperties("vattus")
    private CdtVatTuHoTro cdtVatTuHoTro;

    @ManyToOne
    @JsonIgnoreProperties("vattus")
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

    public CdtVatTu csytid(Long csytid) {
        this.csytid = csytid;
        return this;
    }

    public void setCsytid(Long csytid) {
        this.csytid = csytid;
    }

    public CdtVatTuHoTro getCdtVatTuHoTro() {
        return cdtVatTuHoTro;
    }

    public CdtVatTu cdtVatTuHoTro(CdtVatTuHoTro cdtVatTuHoTro) {
        this.cdtVatTuHoTro = cdtVatTuHoTro;
        return this;
    }

    public void setCdtVatTuHoTro(CdtVatTuHoTro cdtVatTuHoTro) {
        this.cdtVatTuHoTro = cdtVatTuHoTro;
    }

    public CdtChiDaoTuyen getCdtChiDaoTuyen() {
        return cdtChiDaoTuyen;
    }

    public CdtVatTu cdtChiDaoTuyen(CdtChiDaoTuyen cdtChiDaoTuyen) {
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
        if (!(o instanceof CdtVatTu)) {
            return false;
        }
        return id != null && id.equals(((CdtVatTu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CdtVatTu{" +
            "id=" + getId() +
            ", csytid=" + getCsytid() +
            "}";
    }
}
