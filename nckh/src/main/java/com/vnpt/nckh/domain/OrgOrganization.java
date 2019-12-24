package com.vnpt.nckh.domain;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * OrgOrganization entity.\n@author VINHHC.
 */
@ApiModel(description = "OrgOrganization entity.\n@author VINHHC.")
@Entity
@Table(name = "orgzt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrgOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "parent_id")
    private Long parentId;

    @NotNull
    @Min(value = 1L)
    @Max(value = 5L)
    @Column(name = "org_type", nullable = false)
    private Long orgType;

    @Size(max = 255)
    @Column(name = "org_code", length = 255)
    private String orgCode;

    @Size(max = 150)
    @Column(name = "org_name", length = 150)
    private String orgName;

    @Size(max = 150)
    @Column(name = "org_level", length = 150)
    private String orgLevel;

    @Size(max = 255)
    @Column(name = "db_name", length = 255)
    private String dbName;

    @Size(max = 255)
    @Column(name = "db_schema", length = 255)
    private String dbSchema;

    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "province_id")
    private Long provinceId;

    @Min(value = 1L)
    @Max(value = 10L)
    @Column(name = "status")
    private Long status;

    @Size(max = 200)
    @Column(name = "org_address", length = 200)
    private String orgAddress;

    @Size(max = 10)
    @Column(name = "hospital_code", length = 10)
    private String hospitalCode;

    @OneToMany(mappedBy = "orgOrganization")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrgOfficer> orgOfficers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public OrgOrganization parentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getOrgType() {
        return orgType;
    }

    public OrgOrganization orgType(Long orgType) {
        this.orgType = orgType;
        return this;
    }

    public void setOrgType(Long orgType) {
        this.orgType = orgType;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public OrgOrganization orgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public OrgOrganization orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public OrgOrganization orgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
        return this;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getDbName() {
        return dbName;
    }

    public OrgOrganization dbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public OrgOrganization dbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
        return this;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public OrgOrganization provinceId(Long provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getStatus() {
        return status;
    }

    public OrgOrganization status(Long status) {
        this.status = status;
        return this;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public OrgOrganization orgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
        return this;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public OrgOrganization hospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
        return this;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public Set<OrgOfficer> getOrgOfficers() {
        return orgOfficers;
    }

    public OrgOrganization orgOfficers(Set<OrgOfficer> orgOfficers) {
        this.orgOfficers = orgOfficers;
        return this;
    }

    public OrgOrganization addOrgOfficer(OrgOfficer orgOfficer) {
        this.orgOfficers.add(orgOfficer);
        orgOfficer.setOrgOrganization(this);
        return this;
    }

    public OrgOrganization removeOrgOfficer(OrgOfficer orgOfficer) {
        this.orgOfficers.remove(orgOfficer);
        orgOfficer.setOrgOrganization(null);
        return this;
    }

    public void setOrgOfficers(Set<OrgOfficer> orgOfficers) {
        this.orgOfficers = orgOfficers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrgOrganization)) {
            return false;
        }
        return id != null && id.equals(((OrgOrganization) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrgOrganization{" +
            "id=" + getId() +
            ", parentId=" + getParentId() +
            ", orgType=" + getOrgType() +
            ", orgCode='" + getOrgCode() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", orgLevel='" + getOrgLevel() + "'" +
            ", dbName='" + getDbName() + "'" +
            ", dbSchema='" + getDbSchema() + "'" +
            ", provinceId=" + getProvinceId() +
            ", status=" + getStatus() +
            ", orgAddress='" + getOrgAddress() + "'" +
            ", hospitalCode='" + getHospitalCode() + "'" +
            "}";
    }
}
