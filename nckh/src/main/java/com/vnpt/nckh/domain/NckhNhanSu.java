package com.vnpt.nckh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * NckhNhanSu entity.\n@author VINHHC.
 */
@ApiModel(description = "NckhNhanSu entity.\n@author VINHHC.")
@Entity
@Table(name = "nckh_nhan_su")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NckhNhanSu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("nckhNhanSus")
    private OrgOfficer orgOfficer;

    @ManyToOne
    @JsonIgnoreProperties("nckhNhanSus")
    private NckhDetai nckhDetai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrgOfficer getOrgOfficer() {
        return orgOfficer;
    }

    public NckhNhanSu orgOfficer(OrgOfficer orgOfficer) {
        this.orgOfficer = orgOfficer;
        return this;
    }

    public void setOrgOfficer(OrgOfficer orgOfficer) {
        this.orgOfficer = orgOfficer;
    }

    public NckhDetai getNckhDetai() {
        return nckhDetai;
    }

    public NckhNhanSu nckhDetai(NckhDetai nckhDetai) {
        this.nckhDetai = nckhDetai;
        return this;
    }

    public void setNckhDetai(NckhDetai nckhDetai) {
        this.nckhDetai = nckhDetai;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NckhNhanSu)) {
            return false;
        }
        return id != null && id.equals(((NckhNhanSu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NckhNhanSu{" +
            "id=" + getId() +
            "}";
    }
}
